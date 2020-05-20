import org.scalajs.dom.document
import org.scalajs.dom.html
import scala.scalajs.js

class CanvasImageWriter extends ImageWriter {
  private def createCanvas(width: Int, height: Int): html.Canvas = {
    val canvas = document.createElement("canvas")
    canvas.setAttribute("width", width.toString)
    canvas.setAttribute("height", height.toString)
    document.body.appendChild(canvas)

    canvas.asInstanceOf[html.Canvas]
  }

  private def drawPixel(ctx: js.Dynamic, x: Int, y: Int, color: Color): Unit = {
    ctx.fillStyle = s"rgba(${color.r},${color.g},${color.b},255)"
    ctx.fillRect(x, y, 1, 1)
  }

  override def saveImage(image: Image): Unit = {
    val canvas = createCanvas(image.width, image.height)
    val ctx = canvas.getContext("2d")

    for {
      y <- 0 until image.height
      x <- 0 until image.width
    } drawPixel(ctx, x, y, image.pixels(y)(x))
  }
}
