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

object ParamChecker extends ExcelWorkBook with Checker{
  def main(args: Array[String]):Unit ={
//    val c = check(
//      /*args(0)*/,
//      /*args(1)*/,
//      GT_ab("left","right")
//    )
//    println("CHECK1: " +c)


  }

}
