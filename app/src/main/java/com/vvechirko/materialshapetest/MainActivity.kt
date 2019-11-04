package com.vvechirko.materialshapetest

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.internal.ViewUtils.dpToPx
import com.google.android.material.shape.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    @SuppressLint("RestrictedApi")
    fun Int.dp(): Float = dpToPx(this@MainActivity, this)

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        view1.shapeAppearanceModel = ShapeAppearanceModel.builder()
            .setAllCorners(RoundedCornerTreatment())
            .setAllCornerSizes(40.dp())
            .build()

        view2.shapeAppearanceModel = ShapeAppearanceModel.builder()
            .setAllCorners(CutCornerTreatment())
            .setAllCornerSizes(50.dp())
            .build()

        view3.shapeAppearanceModel = ShapeAppearanceModel.builder()
            .setTopLeftCorner(RoundedCornerTreatment())
            .setTopLeftCornerSize(80.dp())
            .setTopRightCorner(RoundedCornerTreatment())
            .setTopRightCornerSize(80.dp())
            .setBottomLeftCorner(RoundedCornerTreatment())
            .setBottomLeftCornerSize(80.dp())
            .build()

        view4.shapeAppearanceModel = ShapeAppearanceModel.builder()
            .setRightEdge(TriangleEdgeTreatment(100.dp(), true))
            .build()

        view5.shapeAppearanceModel = ShapeAppearanceModel.builder()
            .setTopLeftCorner(RoundedCornerTreatment())
            .setAllCornerSizes(50.dp())
            .build()

        view6.shapeAppearanceModel = ShapeAppearanceModel.builder()
            .setAllCorners(RoundedCornerTreatment())
            .setAllCornerSizes(100.dp())
            .build()

        view7.shapeAppearanceModel = ShapeAppearanceModel.builder()
            .setAllCorners(CutCornerTreatment())
            .setAllCornerSizes(100.dp())
            .build()

        view8.shapeAppearanceModel = ShapeAppearanceModel.builder()
            .setTopLeftCorner(RoundedCornerTreatment())
            .setAllCornerSizes(50.dp())
            .setTopEdge(TriangleEdgeTreatment(24.dp(), false))
            .setBottomEdge(TriangleEdgeTreatment(24.dp(), true))
            .build()

        view9.shapeAppearanceModel = ShapeAppearanceModel.builder()
            .setAllCorners(RoundedCornerTreatment())
            .setAllCornerSizes(24.dp())
            .setAllEdges(ArgEdgeTreatment(24.dp(), true))
            .build()

        view10.shapeAppearanceModel = ShapeAppearanceModel.builder()
            .setAllCorners(InnerRoundCornerTreatment())
            .setAllCornerSizes(24.dp())
            .setAllEdges(ArgEdgeTreatment(24.dp(), false))
            .build()

        view11.shapeAppearanceModel = ShapeAppearanceModel.builder()
            .setAllCorners(InnerCutCornerTreatment())
            .setAllCornerSizes(60.dp())
            .build()

        view12.shapeAppearanceModel = ShapeAppearanceModel.builder()
            .setAllCorners(InnerRoundCornerTreatment())
            .setAllCornerSizes(60.dp())
//            .setAllEdges(QuadEdgeTreatment(40.dp()))
            .build()

        view13.shapeAppearanceModel = ShapeAppearanceModel.builder()
            .setAllCorners(ExtraRoundCornerTreatment())
            .setAllCornerSizes(24.dp())
            .setAllEdges(ArgEdgeTreatment(24.dp(), false))
            .build()

        view14.shapeAppearanceModel = ShapeAppearanceModel.builder()
            .setAllCorners(InnerRoundCornerTreatment())
            .setAllCornerSizes(24.dp())
            .setAllEdges(ArgEdgeTreatment(24.dp(), true))
            .build()
    }

    class InnerCutCornerTreatment : CornerTreatment() {
        override fun getCornerPath(shapePath: ShapePath, angle: Float, f: Float, size: Float) {
            val radius = size * f
            shapePath.reset(0f, radius, 180f, 180 - angle)
            shapePath.lineTo(radius, radius)
            shapePath.lineTo(radius, 0f)
        }
    }

    class InnerRoundCornerTreatment : CornerTreatment() {
        override fun getCornerPath(shapePath: ShapePath, angle: Float, f: Float, size: Float) {
            val radius = size * f
            shapePath.reset(0f, radius, 180f, 180 - angle)
            shapePath.addArc(-radius, -radius, radius, radius, angle, -90f)
        }
    }

    class ExtraRoundCornerTreatment : CornerTreatment() {
        override fun getCornerPath(shapePath: ShapePath, angle: Float, f: Float, size: Float) {
            val radius = size * f
            shapePath.reset(0f, radius, 180f, 180 - angle)
            shapePath.addArc(-radius, -radius, radius, radius, angle, 270f)
        }
    }

    class ArgEdgeTreatment(val size: Float, val inside: Boolean) : EdgeTreatment() {
        override fun getEdgePath(length: Float, center: Float, f: Float, shapePath: ShapePath) {
            val radius = size * f
            shapePath.lineTo(center - radius, 0f)
            shapePath.addArc(
                center - radius, -radius,
                center + radius, radius,
                180f,
                if (inside) -180f else 180f
            )
            shapePath.lineTo(length, 0f)
        }
    }

    class QuadEdgeTreatment(val size: Float) : EdgeTreatment() {
        // TODO:
        override fun getEdgePath(length: Float, center: Float, f: Float, shapePath: ShapePath) {
            shapePath.quadToPoint(center, size * f, length, 0f)
        }
    }
}