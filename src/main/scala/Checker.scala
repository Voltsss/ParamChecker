import org.apache.poi.ss.usermodel.{Row, Cell, Sheet}

import scala.collection.mutable.ArrayBuffer

abstract class CheckRule
case class GT_ab(leftName:String,rightName:String) extends CheckRule
case class LT_ab(leftName:String,rightName:String) extends CheckRule


/**
  * Created by yamasyo on 2016/01/27.
  * Memo
  * Cell.getCellType return
  *   0:Numeric
  *   1:String
  *   2:FORMULA?
  */
trait Checker {
  def check(checkFile:String,checkSheetName:String,checkRule:CheckRule): Boolean ={
    val e = new ExcelWorkBook()
    e.bookRead(checkFile)
    e.selectSheet(checkSheetName)
    checkRule match {
      case GT_ab(left,right) => greaterThan(e.getSheet,left,right)
    }

  }

  def greaterThan(sheet:Sheet, leftName:String,rightName:String): Boolean = {
    val leftCheckCol = getCheckCol(sheet, leftName, 5, 1)
    val rightCheckCol = getCheckCol(sheet, rightName, 5, 1)
    leftCheckCol match {
      case Some(ls) => rightCheckCol match {
        case Some(rs) => (ls zip rs).filter(t => t._1.getNumericCellValue > t._2.getNumericCellValue).size <= 0
        case None => false
      }
      case None => false
    }
  }

  def getCheckCol(sheet: Sheet,name:String,searchRow:Int,dataOffset:Int):Option[Seq[Cell]] = {
    searchIndexCell(name,sheet,searchRow) match {
      case Some(c) => Some(getColumn(sheet, c.getColumnIndex, c.getRowIndex + dataOffset, sheet.getLastRowNum))
      case None => None
    }
  }

  def getColumn(sheet:Sheet, colNo:Int, startRow:Int, lastRow:Int):Seq[Cell] = {
    val cols : ArrayBuffer[Cell] = new ArrayBuffer[Cell]
    for(i:Int <- startRow to lastRow){
      cols += sheet.getRow(i).getCell(colNo)
    }
    cols.toSeq
  }

  /**
    * return Cell (NonNull,String)
    */
  def searchIndexCell(indexName:String,sheet:Sheet,searchLine:Int):Option[Cell] = {
    val firstNum = sheet.getFirstRowNum
    val searchNum = firstNum + searchLine
    val rs = Range(firstNum , searchNum).map(n => sheet.getRow(n))
    val lr:List[List[Cell]] =  rs.map(r => row2List(r)).toIndexedSeq.toList
    val sc:List[Cell] = lr.map(r => r.filter(c => c!=null).filter(c => c.getCellType==1)).flatten
    val hc = sc.filter(c => c.getStringCellValue == indexName)
    if(hc.size != 0) Some(hc.head) else None
  }

  def print2Unicode(s : String): Unit ={
    s.foreach(c => print(f"0x$c%02X" + " "))
    println
  }

  def row2List(r : Row) :List[Cell] = {
    val li:ArrayBuffer[Cell] = new ArrayBuffer
    val it = r.cellIterator
    while(it.hasNext){
      li += it.next
    }
    li.toList
  }

}
