import org.apache.poi.ss.usermodel.{Row, Sheet, Cell}

import scala.collection.mutable.ArrayBuffer

/**
  * Created by yamasyo on 2016/01/25.
  */

object ParamChecker extends ExcelWorkBook with Checker{
  def main(args: Array[String]):Unit ={
//    val c = check(
//      /*args(0)*/,
//      /*args(1)*/,
//      GT_ab("left","right")
//    )
//    println("CHECK1: " +c)


  }

/**
  * {
  *   "commands":
  *     [
  *       {
  *         "command" : "LEFT_greaterThan_RIGHT",
  *         "file" : "your/file",
  *         "sheetName" : "name",
  *         "LEFT_name" : "nameLeft",
  *         "RIGHT_name" : "nameRight"
  *       },
  *       {
  *         "command" : "LEFT_equal_Number",
  *         "file" : "your/file",
  *         "sheetName" : "name",
  *         "LEFT_name" : "nameIndex",
  *         "number" : 0
  *       },
  *       {
  *         "command" : "LEFT_equal_NumberOfSubFile",
  *         "file" : "your/file",
  *         "sheetName" : "name",
  *         "LEFT_name" : "nameIndex",
  *         "subFile" : "your/sub/file",
  *         "subSheetName" : "subSheet",
  *         "subDataName" : "subName",
  *         "subDataOffsetDirection" : "Row",
  *         "subDataOffset" : 1,
  *       }
  *
  */
  def commandParser() = {

  }

}
