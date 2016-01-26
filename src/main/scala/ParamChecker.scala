import org.apache.poi.ss.usermodel.{Row, Sheet, Cell}

import scala.collection.mutable.ArrayBuffer

/**
  * Created by yamasyo on 2016/01/25.
  */

abstract class CheckRule
case class GT_ab(leftName:String,rightName:String) extends CheckRule
case class LT_ab(leftName:String,rightName:String) extends CheckRule


/**
  * Memo
  * Cell.getCellType return
  *   0:Numeric
  *   1:String
  *   2:FORMULA?
  */

object ParamChecker extends ExcelWorkBook{
  def main(args: Array[String]):Unit ={
    check(
      "yourfilepath",
      "sheetname",
      GT_ab("入力","")
    )

  }

  def check(checkFile:String,checkSheetName:String,checkRule:CheckRule): Unit ={
    val e = new ExcelWorkBook()
    e.bookRead(checkFile)
    e.selectSheet(checkSheetName)
    checkRule match {
      case GT_ab(left,right) => getCheckRange(e.getSheet,left,15) /*greaterThan(left,right)*/
    }

  }

  def greaterThan(leftName:String,rightName:String): Boolean = {
    false
  }

  def getCheckRange(sheet: Sheet,name:String,startRow:Int):Option[Seq[Cell]] = {
    val rs = Range(sheet.getFirstRowNum , startRow).map(n => sheet.getRow(n))
    val lr:List[List[Cell]] =  rs.map(r => row2List(r)).toIndexedSeq.toList
    println("input:" + name)
    val strCell:List[Cell] = lr.map(r => r.filter(c => c!=null).filter(c => c.getCellType==1)).flatten
    val cc = strCell.filter(c => c.getStringCellValue.contains(name))

    println("contains cell is :" +cc)

    val hc = strCell.filter(c => c.getStringCellValue == name)
    println("hit cell is :" + hc)
    println("hit cell Row:" + hc(0).getRowIndex + "  Col:" + hc(0).getColumnIndex)

    if (hc.length >= 1) Some(hc.flatten) else None

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
