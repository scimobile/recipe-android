package com.sci.recipeandroid.feature.onboarding.customtagcontainer

import android.animation.ValueAnimator
import android.animation.ValueAnimator.AnimatorUpdateListener
import android.annotation.TargetApi
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.graphics.Region
import android.graphics.Shader
import android.graphics.Typeface
import android.os.Build
import android.text.TextUtils
import android.view.MotionEvent
import android.view.View
import androidx.customview.widget.ViewDragHelper
import com.sci.recipeandroid.feature.onboarding.customtagcontainer.Utils.dp2px

class TagView : View {
    /** Border width */
    private var mBorderWidth = 0f

    /** Border radius */
    private var mBorderRadius = 0f

    /** Text size */
    private var mTextSize = 0f

    /** Horizontal padding for this view, include left & right padding(left & right padding are equal */
    private var mHorizontalPadding = 0

    /** Vertical padding for this view, include top & bottom padding(top & bottom padding are equal) */
    private var mVerticalPadding = 0

    /** TagView border color */
    private var mBorderColor = 0

    /** TagView background color */
    var tagBackgroundColor = 0

    /** TagView background color */
    var tagSelectedBackgroundColor = 0

    /** TagView text color */
    private var mTextColor = 0

    /** Whether this view clickable */
    var isViewClickable = false

    /** Whether this view selectable */
    private var isViewSelectable = false

    /** Whether this view selected */
    var isViewSelected = false
        private set

    /** The max length for this tag view */
    private var mTagMaxLength = 0

    /** OnTagClickListener for click action */
    private var mOnTagClickListener: OnTagClickListener? = null

    /** Move slop(default 5dp) */
    private var mMoveSlop = 5

    /** Scroll slop threshold 4dp */
    private var mSlopThreshold = 4

    /** How long trigger long click callback(default 500ms) */
    private val mLongPressTime = 500

    /** Text direction(support:TEXT_DIRECTION_RTL & TEXT_DIRECTION_LTR, default TEXT_DIRECTION_LTR) */
    private var mTextDirection = TEXT_DIRECTION_LTR

    /** The distance between baseline and descent */
    private var bdDistance = 0f

    /** Whether to support 'letters show with RTL(eg: Android to diordnA)' style(default false) */
    var isTagSupportLettersRTL = false
    private var mPaint: Paint? = null
    private var mRipplePaint: Paint? = null
    private var mRectF: RectF? = null
    private var mAbstractText: String? = null
    var text: String? = null
        private set
    private var isUp = false
    private var isMoved = false
    private var isExecLongClick = false
    private var mLastX = 0
    private var mLastY = 0
    private var fontH = 0f
    private var fontW = 0f
    private var mTouchX = 0f
    private var mTouchY = 0f

    /** The ripple effect duration(default 1000ms) */
    private var mRippleDuration = 1000
    private var mRippleRadius = 0f
    private var mRippleColor = 0
    private var mRippleAlpha = 0
    private var mPath: Path? = null
    private var mTypeface: Typeface? = null
    private var mRippleValueAnimator: ValueAnimator? = null
    private var mBitmapImage: Bitmap? = null
    var isEnableCross = false
    var crossAreaWidth = 0f
    var crossAreaPadding = 0f
    var crossColor = 0
    var crossLineWidth = 0f
    private var unSupportedClipPath = false
    private val mLongClickHandle: Runnable = object : Runnable {
        override fun run() {
            if (!isMoved && !isUp) {
                val state: Int = (parent as TagContainerLayout).tagViewState
                if (state == ViewDragHelper.STATE_IDLE) {
                    isExecLongClick = true
                    mOnTagClickListener!!.onTagLongClick(tag as Int, text)
                }
            }
        }
    }

    constructor(context: Context, text: String?) : super(context) {
        init(context, text)
    }

    constructor(context: Context, text: String?, defaultImageID: Int) : super(context) {
        init(context, text)
        mBitmapImage = BitmapFactory.decodeResource(resources, defaultImageID)
    }

    private fun init(context: Context, text: String?) {
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mRipplePaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mRipplePaint!!.style = Paint.Style.FILL
        mRectF = RectF()
        mPath = Path()
        this.text = text ?: ""
        mMoveSlop = dp2px(context, mMoveSlop.toFloat()).toInt()
        mSlopThreshold = dp2px(context, mSlopThreshold.toFloat()).toInt()
    }

    private fun onDealText() {
        mAbstractText = if (!TextUtils.isEmpty(text)) {
            if (text!!.length <= mTagMaxLength) text else text!!.substring(
                0,
                mTagMaxLength - 3
            ) + "..."
        } else {
            ""
        }
        mPaint!!.setTypeface(mTypeface)
        mPaint!!.textSize = mTextSize
        val fontMetrics = mPaint!!.fontMetrics
        fontH = fontMetrics.descent - fontMetrics.ascent
        if (mTextDirection == TEXT_DIRECTION_RTL) {
            fontW = 0f
            for (c in mAbstractText!!.toCharArray()) {
                val sc = c.toString()
                fontW += mPaint!!.measureText(sc)
            }
        } else {
            fontW = mPaint!!.measureText(mAbstractText)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val height = mVerticalPadding * 2 + fontH.toInt()
        val width =
            mHorizontalPadding * 2 + fontW.toInt() + (if (isEnableCross) height else 0) + if (isEnableImage) height else 0
        crossAreaWidth = Math.min(Math.max(crossAreaWidth, height.toFloat()), width.toFloat())
        setMeasuredDimension(width, height)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mRectF!![mBorderWidth, mBorderWidth, w - mBorderWidth] = h - mBorderWidth
    }

    override fun onDraw(canvas: Canvas) {
        // draw background
        mPaint!!.style = Paint.Style.FILL
        mPaint!!.color = if (isViewSelected) tagSelectedBackgroundColor else tagBackgroundColor
        canvas.drawRoundRect(mRectF!!, mBorderRadius, mBorderRadius, mPaint!!)

        // draw border
        mPaint!!.style = Paint.Style.STROKE
        mPaint!!.strokeWidth = mBorderWidth
        mPaint!!.color = mBorderColor
        canvas.drawRoundRect(mRectF!!, mBorderRadius, mBorderRadius, mPaint!!)

        // draw ripple for TagView
        drawRipple(canvas)

        // draw text
        mPaint!!.style = Paint.Style.FILL
        mPaint!!.color = mTextColor
        if (mTextDirection == TEXT_DIRECTION_RTL) {
            if (isTagSupportLettersRTL) {
                var tmpX = ((if (isEnableCross) width + height else width) / 2
                        + fontW / 2)
                for (c in mAbstractText!!.toCharArray()) {
                    val sc = c.toString()
                    tmpX -= mPaint!!.measureText(sc)
                    canvas.drawText(sc, tmpX, height / 2 + fontH / 2 - bdDistance, mPaint!!)
                }
            } else {
                canvas.drawText(
                    mAbstractText!!,
                    (if (isEnableCross) width + fontW else width).toInt() / 2 - fontW / 2,
                    height / 2 + fontH / 2 - bdDistance, mPaint!!
                )
            }
        } else {
            canvas.drawText(
                mAbstractText!!,
                (if (isEnableCross) width - height else width) / 2 - fontW / 2 + if (isEnableImage) height / 2 else 0,
                height / 2 + fontH / 2 - bdDistance, mPaint!!
            )
        }

        // draw cross
        drawCross(canvas)

        // draw image
        drawImage(canvas)
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (isViewClickable) {
            val y = event.y.toInt()
            val x = event.x.toInt()
            val action = event.action
            when (action) {
                MotionEvent.ACTION_DOWN -> {
                    if (parent != null) {
                        parent.requestDisallowInterceptTouchEvent(true)
                    }
                    mLastY = y
                    mLastX = x
                }

                MotionEvent.ACTION_MOVE -> if (!isViewSelected && (Math.abs(mLastY - y) > mSlopThreshold
                            || Math.abs(mLastX - x) > mSlopThreshold)
                ) {
                    if (parent != null) {
                        parent.requestDisallowInterceptTouchEvent(false)
                    }
                    isMoved = true
                    return false
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val action = event.action
        if (action == MotionEvent.ACTION_DOWN) {
            mRippleRadius = 0.0f
            mTouchX = event.x
            mTouchY = event.y
            splashRipple()
        }
        if (isEnableCross && isClickCrossArea(event) && mOnTagClickListener != null) {
            if (action == MotionEvent.ACTION_UP) {
                mOnTagClickListener!!.onTagCrossClick(tag as Int)
            }
            return true
        } else if (isViewClickable && mOnTagClickListener != null) {
            val x = event.x.toInt()
            val y = event.y.toInt()
            when (action) {
                MotionEvent.ACTION_DOWN -> {
                    mLastY = y
                    mLastX = x
                    isMoved = false
                    isUp = false
                    isExecLongClick = false
                    postDelayed(mLongClickHandle, mLongPressTime.toLong())
                }

                MotionEvent.ACTION_MOVE -> {
                    if (isMoved) {

                    }
                    if (Math.abs(mLastX - x) > mMoveSlop || Math.abs(mLastY - y) > mMoveSlop) {
                        isMoved = true
                        if (isViewSelected) {
                            mOnTagClickListener!!.onSelectedTagDrag(tag as Int, text)
                        }
                    }
                }

                MotionEvent.ACTION_UP -> {
                    isUp = true
                    if (!isExecLongClick && !isMoved) {
                        mOnTagClickListener!!.onTagClick(tag as Int, text)
                    }
                }
            }
            return true
        }
        return super.onTouchEvent(event)
    }

    private fun isClickCrossArea(event: MotionEvent): Boolean {
        return if (mTextDirection == TEXT_DIRECTION_RTL) {
            event.x <= crossAreaWidth
        } else event.x >= width - crossAreaWidth
    }

    private fun drawImage(canvas: Canvas) {
        if (isEnableImage) {
            val scaledImageBitmap = Bitmap.createScaledBitmap(
                mBitmapImage!!, Math.round(height - mBorderWidth), Math.round(
                    height - mBorderWidth
                ), false
            )
            val paint = Paint()
            paint.isAntiAlias = true
            paint.setShader(
                BitmapShader(
                    scaledImageBitmap,
                    Shader.TileMode.CLAMP,
                    Shader.TileMode.CLAMP
                )
            )
            val rect =
                RectF(mBorderWidth, mBorderWidth, height - mBorderWidth, height - mBorderWidth)
            canvas.drawRoundRect(rect, rect.height() / 2, rect.height() / 2, paint)
        }
    }

    private fun drawCross(canvas: Canvas) {
        if (isEnableCross) {
            crossAreaPadding =
                if (crossAreaPadding > height / 2) (height / 2).toFloat() else crossAreaPadding
            val ltX: Int
            val ltY: Int
            val rbX: Int
            val rbY: Int
            val lbX: Int
            val lbY: Int
            val rtX: Int
            val rtY: Int
            ltX =
                if (mTextDirection == TEXT_DIRECTION_RTL) crossAreaPadding.toInt() else (width - height + crossAreaPadding).toInt()
            ltY =
                if (mTextDirection == TEXT_DIRECTION_RTL) crossAreaPadding.toInt() else crossAreaPadding.toInt()
            lbX =
                if (mTextDirection == TEXT_DIRECTION_RTL) crossAreaPadding.toInt() else (width - height + crossAreaPadding).toInt()
            lbY =
                if (mTextDirection == TEXT_DIRECTION_RTL) (height - crossAreaPadding).toInt() else (height - crossAreaPadding).toInt()
            rtX =
                if (mTextDirection == TEXT_DIRECTION_RTL) (height - crossAreaPadding).toInt() else (width - crossAreaPadding).toInt()
            rtY =
                if (mTextDirection == TEXT_DIRECTION_RTL) crossAreaPadding.toInt() else crossAreaPadding.toInt()
            rbX =
                if (mTextDirection == TEXT_DIRECTION_RTL) (height - crossAreaPadding).toInt() else (width - crossAreaPadding).toInt()
            rbY =
                if (mTextDirection == TEXT_DIRECTION_RTL) (height - crossAreaPadding).toInt() else (height - crossAreaPadding).toInt()
            mPaint!!.style = Paint.Style.STROKE
            mPaint!!.color = crossColor
            mPaint!!.strokeWidth = crossLineWidth
            canvas.drawLine(ltX.toFloat(), ltY.toFloat(), rbX.toFloat(), rbY.toFloat(), mPaint!!)
            canvas.drawLine(lbX.toFloat(), lbY.toFloat(), rtX.toFloat(), rtY.toFloat(), mPaint!!)
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private fun drawRipple(canvas: Canvas?) {
        if (isViewClickable && Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB && canvas != null && !unSupportedClipPath) {

            // Disable hardware acceleration for 'Canvas.clipPath()' when running on API from 11 to 17
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2) {
                setLayerType(LAYER_TYPE_SOFTWARE, null)
            }
            try {
                canvas.save()
                mPath!!.reset()
                canvas.clipPath(mPath!!)
                mPath!!.addRoundRect(mRectF!!, mBorderRadius, mBorderRadius, Path.Direction.CCW)

//                bug: https://github.com/whilu/AndroidTagView/issues/88
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    canvas.clipPath(mPath!!)
                } else {
                    canvas.clipPath(mPath!!, Region.Op.REPLACE)
                }
                canvas.drawCircle(mTouchX, mTouchY, mRippleRadius, mRipplePaint!!)
                canvas.restore()
            } catch (e: UnsupportedOperationException) {
                unSupportedClipPath = true
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private fun splashRipple() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB && mTouchX > 0 && mTouchY > 0) {
            mRipplePaint!!.color = mRippleColor
            mRipplePaint!!.alpha = mRippleAlpha
            val maxDis = Math.max(
                Math.max(
                    Math.max(mTouchX, mTouchY),
                    Math.abs(measuredWidth - mTouchX)
                ), Math.abs(measuredHeight - mTouchY)
            )
            mRippleValueAnimator =
                ValueAnimator.ofFloat(0.0f, maxDis).setDuration(mRippleDuration.toLong())
            mRippleValueAnimator?.addUpdateListener(AnimatorUpdateListener { animation ->
                val animValue = animation.animatedValue as Float
                mRippleRadius = if (animValue >= maxDis) 0F else animValue
                postInvalidate()
            })
            mRippleValueAnimator?.start()
        }
    }

    fun setTagMaxLength(maxLength: Int) {
        mTagMaxLength = maxLength
        onDealText()
    }

    fun setOnTagClickListener(listener: OnTagClickListener?) {
        mOnTagClickListener = listener
    }

    fun setTagBorderColor(color: Int) {
        mBorderColor = color
    }

    fun setTagTextColor(color: Int) {
        mTextColor = color
    }

    fun setBorderWidth(width: Float) {
        mBorderWidth = width
    }

    fun setBorderRadius(radius: Float) {
        mBorderRadius = radius
    }

    fun setTextSize(size: Float) {
        mTextSize = size
        onDealText()
    }

    fun setHorizontalPadding(padding: Int) {
        mHorizontalPadding = padding
    }

    fun setVerticalPadding(padding: Int) {
        mVerticalPadding = padding
    }

    fun setImage(newImage: Bitmap?) {
        mBitmapImage = newImage
        this.invalidate()
    }

    fun setIsViewSelectable(viewSelectable: Boolean) {
        isViewSelectable = viewSelectable
    }

    //TODO change background color
    fun selectView() {
        if (isViewSelectable && !isViewSelected) {
            isViewSelected = true
            postInvalidate()
        }
    }

    fun deselectView() {
        if (isViewSelectable && isViewSelected) {
            isViewSelected = false
            postInvalidate()
        }
    }

    interface OnTagClickListener {
        fun onTagClick(position: Int, text: String?)
        fun onTagLongClick(position: Int, text: String?)
        fun onSelectedTagDrag(position: Int, text: String?)
        fun onTagCrossClick(position: Int)
    }

    override fun getTextDirection(): Int {
        return mTextDirection
    }

    override fun setTextDirection(textDirection: Int) {
        mTextDirection = textDirection
    }

    fun setTypeface(typeface: Typeface?) {
        mTypeface = typeface
        onDealText()
    }

    fun setRippleAlpha(mRippleAlpha: Int) {
        this.mRippleAlpha = mRippleAlpha
    }

    fun setRippleColor(mRippleColor: Int) {
        this.mRippleColor = mRippleColor
    }

    fun setRippleDuration(mRippleDuration: Int) {
        this.mRippleDuration = mRippleDuration
    }

    fun setBdDistance(bdDistance: Float) {
        this.bdDistance = bdDistance
    }

    val isEnableImage: Boolean
        get() = mBitmapImage != null && mTextDirection != TEXT_DIRECTION_RTL
}


