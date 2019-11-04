package com.vvechirko.materialshapetest

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.MaterialShapeUtils
import com.google.android.material.shape.RoundedCornerTreatment
import com.google.android.material.shape.ShapeAppearanceModel

class MaterialShapeView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    val shapeDrawable = MaterialShapeDrawable()

    init {
        // hardCoded dimensions
        val shapeAppearanceModel = ShapeAppearanceModel.builder()
            .setAllCorners(RoundedCornerTreatment())
            .setAllCornerSizes(56f)
            .build()

        shapeDrawable.shapeAppearanceModel = shapeAppearanceModel
        shapeDrawable.shadowCompatibilityMode = MaterialShapeDrawable.SHADOW_COMPAT_MODE_ALWAYS
        shapeDrawable.initializeElevationOverlay(context)
        shapeDrawable.paintStyle = Paint.Style.FILL
        shapeDrawable.tintList = backgroundTintList
        shapeDrawable.elevation = elevation

        background = shapeDrawable
    }

    override fun setBackgroundTintList(tint: ColorStateList?) {
        // Possible null when parent constructor is called
        if (shapeDrawable != null) {
            shapeDrawable.tintList = tint
        } else {
            super.setBackgroundTintList(tint)
        }
    }

    override fun setElevation(elevation: Float) {
        // Possible null when parent constructor is called
        if (shapeDrawable != null) {
            shapeDrawable.elevation = elevation
        } else {
            super.setElevation(elevation)
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        MaterialShapeUtils.setParentAbsoluteElevation(this, shapeDrawable)
        (parent as? ViewGroup)?.clipChildren = false
    }
}