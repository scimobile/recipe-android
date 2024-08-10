package com.sci.recipeandroid.feature.onboarding.customtagcontainer

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.customview.widget.ViewDragHelper
import com.sci.recipeandroid.R
import com.sci.recipeandroid.feature.onboarding.customtagcontainer.Utils.dp2px
import com.sci.recipeandroid.feature.onboarding.customtagcontainer.Utils.sp2px
import java.util.Arrays
import java.util.Collections


class TagContainerLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    ViewGroup(context, attrs, defStyleAttr) {
    /**
     * Get vertical interval in this view.
     *
     * @return
     */
    /**
     * Vertical interval, default 5(dp)
     */
    var verticalInterval = 0
        private set

    /**
     * The list to store the tags color info
     */
    private var mColorArrayList: List<IntArray>? = null
    /**
     * Get horizontal interval in this view.
     *
     * @return
     */
    /**
     * Horizontal interval, default 5(dp)
     */
    var horizontalInterval = 0
        private set
    /**
     * Get TagContainerLayout border width.
     *
     * @return
     */
    /**
     * Set TagContainerLayout border width.
     *
     * @param width
     */
    /**
     * TagContainerLayout border width(default 0.5dp)
     */
    var borderWidth = 0.5f
    /**
     * Get TagContainerLayout border radius.
     *
     * @return
     */
    /**
     * Set TagContainerLayout border radius.
     *
     * @param radius
     */
    /**
     * TagContainerLayout border radius(default 10.0dp)
     */
    var borderRadius = 10.0f
    /**
     * Get TagContainerLayout ViewDragHelper sensitivity.
     *
     * @return
     */
    /**
     * Set TagContainerLayout ViewDragHelper sensitivity.
     *
     * @param sensitivity
     */
    /**
     * The sensitive of the ViewDragHelper(default 1.0f, normal)
     */
    var sensitivity = 1.0f

    /**
     * TagView average height
     */
    private var mChildHeight = 0
    /**
     * Get TagContainerLayout border color.
     *
     * @return
     */
    /**
     * Set TagContainerLayout border color.
     *
     * @param color
     */
    /**
     * TagContainerLayout border color(default #22FF0000)
     */
    var borderColor = Color.parseColor("#22FF0000")

    /**
     * TagContainerLayout background color(default #11FF0000)
     */
    private var mBackgroundColor = Color.parseColor("#11FF0000")
    /**
     * Get container layout gravity.
     *
     * @return
     */
    /**
     * Set container layout gravity.
     *
     * @param gravity
     */
    /**
     * The container layout gravity(default left)
     */
    var gravity = Gravity.LEFT

    /**
     * The max line count of TagContainerLayout
     */
    private var mMaxLines = 0

    /**
     * The max length for TagView(default max length 23)
     */
    private var mTagMaxLength = 23
    /**
     * Get TagView border width.
     *
     * @return
     */
    /**
     * Set TagView border width.
     *
     * @param width
     */
    /**
     * TagView Border width(default 0.5dp)
     */
    var tagBorderWidth = 0.5f
    /**
     * Get TagView border radius.
     *
     * @return
     */
    /**
     * Set TagView border radius.
     *
     * @param radius
     */
    /**
     * TagView Border radius(default 15.0dp)
     */
    var tagBorderRadius = 15.0f
    /**
     * Get TagView text size.
     *
     * @return
     */
    /**
     * Set TagView text size.
     *
     * @param size
     */
    /**
     * TagView Text size(default 14sp)
     */
    var tagTextSize = 14f
    /**
     * Get tag text direction
     *
     * @return
     */
    /**
     * Set tag text direction, support:View.TEXT_DIRECTION_RTL and View.TEXT_DIRECTION_LTR,
     * default View.TEXT_DIRECTION_LTR
     *
     * @param textDirection
     */
    /**
     * Text direction(support:TEXT_DIRECTION_RTL & TEXT_DIRECTION_LTR, default TEXT_DIRECTION_LTR)
     */
    var tagTextDirection = TEXT_DIRECTION_LTR

    /**
     * Horizontal padding for TagView, include left & right padding(left & right padding are equal, default 10dp)
     */
    private var mTagHorizontalPadding = 10

    /**
     * Vertical padding for TagView, include top & bottom padding(top & bottom padding are equal, default 8dp)
     */
    private var mTagVerticalPadding = 8
    /**
     * Get TagView border color.
     *
     * @return
     */
    /**
     * Set TagView border color.
     *
     * @param color
     */
    /**
     * TagView border color(default #88F44336)
     */
    var tagBorderColor = Color.parseColor("#88F44336")
    /**
     * Get TagView background color.
     *
     * @return
     */
    /**
     * Set TagView background color.
     *
     * @param color
     */
    /**
     * TagView background color(default #33F44336)
     */
    var tagBackgroundColor = Color.parseColor("#33F44336")

    /**
     * Selected TagView background color(default #33FF7669)
     */
    private val mSelectedTagBackgroundColor = Color.parseColor("#33FF7669")
    /**
     * Get TagView text color.
     *
     * @return
     */
    /**
     * Set TagView text color.
     *
     * @param color
     */
    /**
     * TagView text color(default #FF666666)
     */
    var tagTextColor = Color.parseColor("#FF666666")
    /**
     * Get TagView typeface.
     *
     * @return
     */
    /**
     * Set TagView typeface.
     *
     * @param typeface
     */
    /**
     * TagView typeface
     */
    var tagTypeface = Typeface.DEFAULT
    /**
     * Get TagView is clickable.
     *
     * @return
     */
    /**
     * Set TagView is clickable
     *
     * @param clickable
     */
    /**
     * Whether TagView can clickable(default unclickable)
     */
    var isTagViewClickable = false
    /**
     * Get TagView is selectable.
     *
     * @return
     */
    /**
     * Set TagView is selectable
     *
     * @param selectable
     */
    /**
     * Whether TagView can selectable(default unselectable)
     */
    var isTagViewSelectable = false

    /**
     * Tags
     */
    private var mTags: List<String>? = null
    /**
     * Get default tag image
     *
     * @return
     */
    /**
     * Set default image for tags.
     *
     * @param imageID
     */
    /**
     * Default image for new tags
     */
    var defaultImageDrawableID = -1
    /**
     * Get current view is drag enable attribute.
     *
     * @return
     */
    /**
     * Set whether the child view can be dragged.
     *
     * @param enable
     */
    /**
     * Can drag TagView(default false)
     */
    var dragEnable = false
    /**
     * Get current drag view state.
     *
     * @return
     */
    /**
     * TagView drag state(default STATE_IDLE)
     */
    var tagViewState = ViewDragHelper.STATE_IDLE
        private set

    /**
     * The distance between baseline and descent(default 2.75dp)
     */
    private var mTagBdDistance = 2.75f

    /**
     * OnTagClickListener for TagView
     */
    private var mOnTagClickListener: TagView.OnTagClickListener? = null
    /**
     * Get the 'letters show with RTL(like: Android to diordnA)' style if it's enabled
     *
     * @return
     */
    /**
     * Set whether the 'support letters show with RTL(like: Android to diordnA)' style is enabled.
     *
     * @param mTagSupportLettersRTL
     */
    /**
     * Whether to support 'letters show with RTL(eg: Android to diordnA)' style(default false)
     */
    var isTagSupportLettersRTL = false
    private var mPaint: Paint? = null
    private var mRectF: RectF? = null
    private var mViewDragHelper: ViewDragHelper? = null
    private var mChildViews: MutableList<View>? = null
    private lateinit var mViewPos: IntArray
    /**
     * Get TagView theme.
     *
     * @return
     */
    /**
     * Set TagView theme.
     *
     * @param theme
     */
    /**
     * View theme(default PURE_CYAN)
     */
    var theme: Int = 1
    /**
     * Get the ripple effect duration.
     *
     * @return
     */
    /**
     * Set TagView ripple effect duration, default is 1000ms.
     *
     * @param mRippleDuration
     */
    /**
     * The ripple effect duration(In milliseconds, default 1000ms)
     */
    var rippleDuration = 1000
    /**
     * Get the ripple effect color.
     *
     * @return
     */
    /**
     * Set TagView ripple effect color.
     *
     * @param mRippleColor
     */
    /**
     * The ripple effect color(default #EEEEEE)
     */
    var rippleColor = 0
    /**
     * Get the ripple effect color's alpha.
     *
     * @return
     */
    /**
     * Set TagView ripple effect alpha, the value may between 0 to 255, default is 128.
     *
     * @param mRippleAlpha
     */
    /**
     * The ripple effect color alpha(the value may between 0 - 255, default 128)
     */
    var rippleAlpha = 128
    /**
     * Get is the TagView's cross enable, default false.
     *
     * @return
     */
    /**
     * Enable or disable the TagView's cross.
     *
     * @param mEnableCross
     */
    /**
     * Enable draw cross icon(default false)
     */
    var isEnableCross = false
    /**
     * Get TagView cross area width.
     *
     * @return
     */
    /**
     * Set TagView area width.
     *
     * @param mCrossAreaWidth
     */
    /**
     * The cross area width(your cross click area, default equal to the TagView's height)
     */
    var crossAreaWidth = 0.0f
    /**
     * Get agView cross area's padding.
     *
     * @return
     */
    /**
     * Set TagView cross area padding, default 10dp.
     *
     * @param mCrossAreaPadding
     */
    /**
     * The padding of the cross area(default 10dp)
     */
    var crossAreaPadding = 10.0f
    /**
     * Set TagView cross color.
     *
     * @return
     */
    /**
     * Set TagView cross color, default Color.BLACK.
     *
     * @param mCrossColor
     */
    /**
     * The cross icon color(default Color.BLACK)
     */
    var crossColor = Color.BLACK
    /**
     * Get TagView cross line width.
     *
     * @return
     */
    /**
     * Set TagView cross line width, default 1dp.
     *
     * @param mCrossLineWidth
     */
    /**
     * The cross line width(default 1dp)
     */
    var crossLineWidth = 1.0f
    /**
     * Get TagView background resource
     * @return
     */
    /**
     * Set TagView background resource
     * @param tagBackgroundResource
     */
    /**
     * TagView background resource
     */
    var tagBackgroundResource = 0

    init {
        init(context, attrs, defStyleAttr)
    }

    private fun init(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {
        val attributes = context.obtainStyledAttributes(
            attrs, R.styleable.AndroidTagView,
            defStyleAttr, 0
        )
        verticalInterval = attributes.getDimension(
            R.styleable.AndroidTagView_vertical_interval,
            dp2px(context, DEFAULT_INTERVAL)
        ).toInt()
        horizontalInterval = attributes.getDimension(
            R.styleable.AndroidTagView_horizontal_interval,
            dp2px(context, DEFAULT_INTERVAL)
        ).toInt()
        borderWidth = attributes.getDimension(
            R.styleable.AndroidTagView_container_border_width,
            dp2px(context, borderWidth)
        )
        borderRadius = attributes.getDimension(
            R.styleable.AndroidTagView_container_border_radius,
            dp2px(context, borderRadius)
        )
        mTagBdDistance = attributes.getDimension(
            R.styleable.AndroidTagView_tag_bd_distance,
            dp2px(context, mTagBdDistance)
        )
        borderColor = attributes.getColor(
            R.styleable.AndroidTagView_container_border_color,
            borderColor
        )
        mBackgroundColor = attributes.getColor(
            R.styleable.AndroidTagView_container_background_color,
            mBackgroundColor
        )
        dragEnable = attributes.getBoolean(R.styleable.AndroidTagView_container_enable_drag, false)
        sensitivity = attributes.getFloat(
            R.styleable.AndroidTagView_container_drag_sensitivity,
            sensitivity
        )
        gravity = attributes.getInt(R.styleable.AndroidTagView_container_gravity, gravity)
        mMaxLines = attributes.getInt(R.styleable.AndroidTagView_container_max_lines, mMaxLines)
        mTagMaxLength = attributes.getInt(R.styleable.AndroidTagView_tag_max_length, mTagMaxLength)
        theme = attributes.getInt(R.styleable.AndroidTagView_tag_theme, theme)
        tagBorderWidth = attributes.getDimension(
            R.styleable.AndroidTagView_tag_border_width,
            dp2px(context, tagBorderWidth)
        )
        tagBorderRadius = attributes.getDimension(
            R.styleable.AndroidTagView_tag_corner_radius, dp2px(context, tagBorderRadius)
        )
        mTagHorizontalPadding = attributes.getDimension(
            R.styleable.AndroidTagView_tag_horizontal_padding,
            dp2px(context, mTagHorizontalPadding.toFloat())
        ).toInt()
        mTagVerticalPadding = attributes.getDimension(
            R.styleable.AndroidTagView_tag_vertical_padding, dp2px(context, mTagVerticalPadding.toFloat())
        ).toInt()
        tagTextSize = attributes.getDimension(
            R.styleable.AndroidTagView_tag_text_size,
            sp2px(context, tagTextSize)
        )
        tagBorderColor = attributes.getColor(
            R.styleable.AndroidTagView_tag_border_color,
            tagBorderColor
        )
        tagBackgroundColor = attributes.getColor(
            R.styleable.AndroidTagView_tag_background_color,
            tagBackgroundColor
        )
        tagTextColor = attributes.getColor(
            R.styleable.AndroidTagView_tag_text_color,
            tagTextColor
        )
        tagTextDirection = attributes.getInt(
            R.styleable.AndroidTagView_tag_text_direction,
            tagTextDirection
        )
        isTagViewClickable = attributes.getBoolean(R.styleable.AndroidTagView_tag_clickable, false)
        isTagViewSelectable =
            attributes.getBoolean(R.styleable.AndroidTagView_tag_selectable, false)
        rippleColor = attributes.getColor(
            R.styleable.AndroidTagView_tag_ripple_color,
            Color.parseColor("#EEEEEE")
        )
        rippleAlpha = attributes.getInteger(
            R.styleable.AndroidTagView_tag_ripple_alpha,
            rippleAlpha
        )
        rippleDuration = attributes.getInteger(
            R.styleable.AndroidTagView_tag_ripple_duration,
            rippleDuration
        )
        isEnableCross = attributes.getBoolean(
            R.styleable.AndroidTagView_tag_enable_cross,
            isEnableCross
        )
        crossAreaWidth = attributes.getDimension(
            R.styleable.AndroidTagView_tag_cross_width,
            dp2px(context, crossAreaWidth)
        )
        crossAreaPadding = attributes.getDimension(
            R.styleable.AndroidTagView_tag_cross_area_padding,
            dp2px(context, crossAreaPadding)
        )
        crossColor = attributes.getColor(
            R.styleable.AndroidTagView_tag_cross_color,
            crossColor
        )
        crossLineWidth = attributes.getDimension(
            R.styleable.AndroidTagView_tag_cross_line_width,
            dp2px(context, crossLineWidth)
        )
        isTagSupportLettersRTL = attributes.getBoolean(
            R.styleable.AndroidTagView_tag_support_letters_rlt,
            isTagSupportLettersRTL
        )
        tagBackgroundResource = attributes.getResourceId(
            R.styleable.AndroidTagView_tag_background,
            tagBackgroundResource
        )
        attributes.recycle()
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mRectF = RectF()
        mChildViews = ArrayList()
        mViewDragHelper = ViewDragHelper.create(this, sensitivity, DragHelperCallBack())
        setWillNotDraw(false)
        tagMaxLength = mTagMaxLength
        tagHorizontalPadding = mTagHorizontalPadding
        tagVerticalPadding = mTagVerticalPadding
        if (isInEditMode) {
            addTag("sample tag")
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        measureChildren(widthMeasureSpec, heightMeasureSpec)
        val childCount = childCount
        val lines = if (childCount == 0) 0 else getChildLines(childCount)
        val widthSpecSize = MeasureSpec.getSize(widthMeasureSpec)
        //        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        val heightSpecSize = MeasureSpec.getSize(heightMeasureSpec)
        val heightSpecMode = MeasureSpec.getMode(heightMeasureSpec)
        if (childCount == 0) {
            setMeasuredDimension(0, 0)
        } else if (heightSpecMode == MeasureSpec.AT_MOST
            || heightSpecMode == MeasureSpec.UNSPECIFIED
        ) {
            setMeasuredDimension(
                widthSpecSize, ((verticalInterval + mChildHeight) * lines
                        - verticalInterval) + paddingTop + paddingBottom
            )
        } else {
            setMeasuredDimension(widthSpecSize, heightSpecSize)
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mRectF!![0f, 0f, w.toFloat()] = h.toFloat()
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var childCount: Int
        if (getChildCount().also { childCount = it } <= 0) {
            return
        }
        val availableW = measuredWidth - paddingLeft - paddingRight
        var curRight = measuredWidth - paddingRight
        var curTop = paddingTop
        var curLeft = paddingLeft
        var sPos = 0
        mViewPos = IntArray(childCount * 2)
        for (i in 0 until childCount) {
            val childView = getChildAt(i)
            if (childView.visibility != GONE) {
                val width = childView.measuredWidth
                if (gravity == Gravity.RIGHT) {
                    if (curRight - width < paddingLeft) {
                        curRight = measuredWidth - paddingRight
                        curTop += mChildHeight + verticalInterval
                    }
                    mViewPos[i *  2]= curRight - width
                    mViewPos[i * 2 + 1] = curTop
                    curRight -= width + horizontalInterval
                } else if (gravity == Gravity.CENTER) {
                    if (curLeft + width - paddingLeft > availableW) {
                        val leftW = (measuredWidth - mViewPos[(i - 1) * 2]
                                - getChildAt(i - 1).measuredWidth - paddingRight)
                        for (j in sPos until i) {
                            mViewPos[j * 2] = mViewPos[j * 2] + leftW / 2
                        }
                        sPos = i
                        curLeft = paddingLeft
                        curTop += mChildHeight + verticalInterval
                    }
                    mViewPos[i * 2] = curLeft
                    mViewPos[i * 2 + 1] = curTop
                    curLeft += width + horizontalInterval
                    if (i == childCount - 1) {
                        val leftW = (measuredWidth - mViewPos[i * 2]
                                - childView.measuredWidth - paddingRight)
                        for (j in sPos until childCount) {
                            mViewPos[j * 2] = mViewPos[j * 2] + leftW / 2
                        }
                    }
                } else {
                    if (curLeft + width - paddingLeft > availableW) {
                        curLeft = paddingLeft
                        curTop += mChildHeight + verticalInterval
                    }
                    mViewPos[i * 2] = curLeft
                    mViewPos[i * 2 + 1] = curTop
                    curLeft += width + horizontalInterval
                }
            }
        }

        // layout all child views
        for (i in 0 until mViewPos.size / 2) {
            val childView = getChildAt(i)
            childView.layout(
                mViewPos[i * 2], mViewPos[i * 2 + 1],
                mViewPos[i * 2] + childView.measuredWidth,
                mViewPos[i * 2 + 1] + mChildHeight
            )
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        mPaint!!.style = Paint.Style.FILL
        mPaint!!.color = mBackgroundColor
        canvas.drawRoundRect(mRectF!!, borderRadius, borderRadius, mPaint!!)
        mPaint!!.style = Paint.Style.STROKE
        mPaint!!.strokeWidth = borderWidth
        mPaint!!.color = borderColor
        canvas.drawRoundRect(mRectF!!, borderRadius, borderRadius, mPaint!!)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return mViewDragHelper!!.shouldInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        mViewDragHelper!!.processTouchEvent(event)
        return true
    }

    override fun computeScroll() {
        super.computeScroll()
        if (mViewDragHelper!!.continueSettling(true)) {
            requestLayout()
        }
    }

    private fun getChildLines(childCount: Int): Int {
        val availableW = measuredWidth - paddingLeft - paddingRight
        var lines = 1
        var i = 0
        var curLineW = 0
        while (i < childCount) {
            val childView = getChildAt(i)
            val dis = childView.measuredWidth + horizontalInterval
            val height = childView.measuredHeight
            mChildHeight = if (i == 0) height else Math.min(mChildHeight, height)
            curLineW += dis
            if (curLineW - horizontalInterval > availableW) {
                lines++
                curLineW = dis
            }
            i++
        }
        return if (mMaxLines <= 0) lines else mMaxLines
    }

    private fun onUpdateColorFactory(): IntArray {
        val colors: IntArray
        colors = intArrayOf(
                tagBackgroundColor,
                tagBorderColor,
                tagTextColor, mSelectedTagBackgroundColor
            )

        return colors
    }

    private fun onSetTag() {
        if (mTags == null) {
            throw RuntimeException("NullPointer exception!")
        }
        removeAllTags()
        if (mTags!!.size == 0) {
            return
        }
        for (i in mTags!!.indices) {
            onAddTag(mTags!![i], mChildViews!!.size)
        }
        postInvalidate()
    }

    private fun onAddTag(text: String, position: Int) {
        if (position < 0 || position > mChildViews!!.size) {
            throw RuntimeException("Illegal position!")
        }
        val tagView: TagView
        tagView = if (defaultImageDrawableID != -1) {
            TagView(context, text, defaultImageDrawableID)
        } else {
            TagView(context, text)
        }
        initTagView(tagView, position)
        mChildViews!!.add(position, tagView)
        if (position < mChildViews!!.size) {
            for (i in position until mChildViews!!.size) {
                mChildViews!![i].tag = i
            }
        } else {
            tagView.tag = position
        }
        addView(tagView, position)
    }

    private fun initTagView(tagView: TagView, position: Int) {
        val colors: IntArray
        colors = if (mColorArrayList != null && mColorArrayList!!.size > 0) {
            if (mColorArrayList!!.size == mTags!!.size &&
                mColorArrayList!![position].size >= 4
            ) {
                mColorArrayList!![position]
            } else {
                throw RuntimeException("Illegal color list!")
            }
        } else {
            onUpdateColorFactory()
        }
        tagView.tagBackgroundColor = (colors[0])
        tagView.setTagBorderColor(colors[1])
        tagView.setTagTextColor(colors[2])
        tagView.tagSelectedBackgroundColor = (colors[3])
        tagView.setTagMaxLength(mTagMaxLength)
        tagView.textDirection = tagTextDirection
        tagView.setTypeface(tagTypeface)
        tagView.setBorderWidth(tagBorderWidth)
        tagView.setBorderRadius(tagBorderRadius)
        tagView.setTextSize(tagTextSize)
        tagView.setHorizontalPadding(mTagHorizontalPadding)
        tagView.setVerticalPadding(mTagVerticalPadding)
        tagView.isViewClickable = (isTagViewClickable)
        tagView.setIsViewSelectable(isTagViewSelectable)
        tagView.setBdDistance(mTagBdDistance)
        tagView.setOnTagClickListener(mOnTagClickListener)
        tagView.setRippleAlpha(rippleAlpha)
        tagView.setRippleColor(rippleColor)
        tagView.setRippleDuration(rippleDuration)
        tagView.isEnableCross = (isEnableCross)
        tagView.crossAreaWidth = (crossAreaWidth)
        tagView.crossAreaPadding = (crossAreaPadding)
        tagView.crossColor = (crossColor)
        tagView.crossLineWidth = (crossLineWidth)
        tagView.isTagSupportLettersRTL = (isTagSupportLettersRTL)
        tagView.setBackgroundResource(tagBackgroundResource)
    }

    private fun invalidateTags() {
        for (view in mChildViews!!) {
            val tagView = view as TagView
            tagView.setOnTagClickListener(mOnTagClickListener)
        }
    }

    private fun onRemoveTag(position: Int) {
        if (position < 0 || position >= mChildViews!!.size) {
            throw RuntimeException("Illegal position!")
        }
        mChildViews!!.removeAt(position)
        removeViewAt(position)
        for (i in position until mChildViews!!.size) {
            mChildViews!![i].tag = i
        }
        // TODO, make removed view null?
    }

    private fun onRemoveConsecutiveTags(positions: List<Int>) {
        val smallestPosition = Collections.min(positions)
        for (position in positions) {
            if (position < 0 || position >= mChildViews!!.size) {
                throw RuntimeException("Illegal position!")
            }
            mChildViews!!.removeAt(smallestPosition)
            removeViewAt(smallestPosition)
        }
        for (i in smallestPosition until mChildViews!!.size) {
            mChildViews!![i].tag = i
        }
        // TODO, make removed view null?
    }

    private fun onGetNewPosition(view: View): IntArray {
        val left = view.left
        val top = view.top
        var bestMatchLeft = mViewPos[view.tag as Int * 2]
        var bestMatchTop = mViewPos[view.tag as Int * 2 + 1]
        var tmpTopDis = Math.abs(top - bestMatchTop)
        for (i in 0 until mViewPos.size / 2) {
            if (Math.abs(top - mViewPos[i * 2 + 1]) < tmpTopDis) {
                bestMatchTop = mViewPos[i * 2 + 1]
                tmpTopDis = Math.abs(top - mViewPos[i * 2 + 1])
            }
        }
        var rowChildCount = 0
        var tmpLeftDis = 0
        for (i in 0 until mViewPos.size / 2) {
            if (mViewPos[i * 2 + 1] == bestMatchTop) {
                if (rowChildCount == 0) {
                    bestMatchLeft = mViewPos[i * 2]
                    tmpLeftDis = Math.abs(left - bestMatchLeft)
                } else {
                    if (Math.abs(left - mViewPos[i * 2]) < tmpLeftDis) {
                        bestMatchLeft = mViewPos[i * 2]
                        tmpLeftDis = Math.abs(left - bestMatchLeft)
                    }
                }
                rowChildCount++
            }
        }
        return intArrayOf(bestMatchLeft, bestMatchTop)
    }

    private fun onGetCoordinateReferPos(left: Int, top: Int): Int {
        var pos = 0
        for (i in 0 until mViewPos.size / 2) {
            if (left == mViewPos[i * 2] && top == mViewPos[i * 2 + 1]) {
                pos = i
            }
        }
        return pos
    }

    private fun onChangeView(view: View, newPos: Int, originPos: Int) {
        mChildViews!!.removeAt(originPos)
        mChildViews!!.add(newPos, view)
        for (child in mChildViews!!) {
            child.tag = mChildViews!!.indexOf(child)
        }
        removeViewAt(originPos)
        addView(view, newPos)
    }

    private fun ceilTagBorderWidth(): Int {
        return Math.ceil(tagBorderWidth.toDouble()).toInt()
    }

    private inner class DragHelperCallBack : ViewDragHelper.Callback() {
        override fun onViewDragStateChanged(state: Int) {
            super.onViewDragStateChanged(state)
            tagViewState = state
        }

        override fun tryCaptureView(child: View, pointerId: Int): Boolean {
            requestDisallowInterceptTouchEvent(true)
            return dragEnable
        }

        override fun clampViewPositionHorizontal(child: View, left: Int, dx: Int): Int {
            val leftX = paddingLeft
            val rightX = width - child.width - paddingRight
            return Math.min(Math.max(left, leftX), rightX)
        }

        override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int {
            val topY = paddingTop
            val bottomY = height - child.height - paddingBottom
            return Math.min(Math.max(top, topY), bottomY)
        }

        override fun getViewHorizontalDragRange(child: View): Int {
            return measuredWidth - child.measuredWidth
        }

        override fun getViewVerticalDragRange(child: View): Int {
            return measuredHeight - child.measuredHeight
        }

        override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
            super.onViewReleased(releasedChild, xvel, yvel)
            requestDisallowInterceptTouchEvent(false)
            val pos = onGetNewPosition(releasedChild)
            val posRefer = onGetCoordinateReferPos(pos[0], pos[1])
            onChangeView(releasedChild, posRefer, releasedChild.tag as Int)
            mViewDragHelper!!.settleCapturedViewAt(pos[0], pos[1])
            invalidate()
        }
    }

    var tagBdDistance: Float
        /**
         * Get TagView text baseline and descent distance.
         *
         * @return
         */
        get() = mTagBdDistance
        /**
         * Set TagView text baseline and descent distance.
         *
         * @param tagBdDistance
         */
        set(tagBdDistance) {
            mTagBdDistance = dp2px(context, tagBdDistance)
        }

    /**
     * Set tags with own color
     *
     * @param tags
     * @param colorArrayList
     */
    fun setTags(tags: List<String>?, colorArrayList: List<IntArray>?) {
        mTags = tags
        mColorArrayList = colorArrayList
        onSetTag()
    }

    /**
     * Set tags
     *
     * @param tags
     */
    fun setTags(vararg tags: String?) {
        mTags = Arrays.asList(*tags) as List<String>?
        onSetTag()
    }
    /**
     * Inserts the specified TagView into this ContainerLayout at the specified location.
     * The TagView is inserted before the current element at the specified location.
     *
     * @param text
     * @param position
     */
    /**
     * Inserts the specified TagView into this ContainerLayout at the end.
     *
     * @param text
     */
    @JvmOverloads
    fun addTag(text: String, position: Int = mChildViews!!.size) {
        onAddTag(text, position)
        postInvalidate()
    }

    /**
     * Remove a TagView in specified position.
     *
     * @param position
     */
    fun removeTag(position: Int) {
        onRemoveTag(position)
        postInvalidate()
    }

    /**
     * Remove TagView in multiple consecutive positions.
     *
     *
     */
    fun removeConsecutiveTags(positions: List<Int>) {
        onRemoveConsecutiveTags(positions)
        postInvalidate()
    }

    /**
     * Remove all TagViews.
     */
    fun removeAllTags() {
        mChildViews!!.clear()
        removeAllViews()
        postInvalidate()
    }

    /**
     * Set OnTagClickListener for TagView.
     *
     * @param listener
     */
    fun setOnTagClickListener(listener: TagView.OnTagClickListener?) {
        mOnTagClickListener = listener
        invalidateTags()
    }

    /**
     * Toggle select a tag
     *
     * @param position
     */
    fun toggleSelectTagView(position: Int) {
        if (isTagViewSelectable) {
            val tagView = mChildViews!![position] as TagView
            if (tagView.isViewSelected) {
                tagView.deselectView()
            } else {
                tagView.selectView()
            }
        }
    }

    /**
     * Select a tag
     *
     * @param position
     */
    fun selectTagView(position: Int) {
        if (isTagViewSelectable) (mChildViews!![position] as TagView).selectView()
    }

    /**
     * Deselect a tag
     *
     * @param position
     */
    fun deselectTagView(position: Int) {
        if (isTagViewSelectable) (mChildViews!![position] as TagView).deselectView()
    }

    val selectedTagViewPositions: List<Int>
        /**
         * Return selected TagView positions
         *
         * @return list of selected positions
         */
        get() {
            val selectedPositions: MutableList<Int> = ArrayList()
            for (i in mChildViews!!.indices) {
                if ((mChildViews!![i] as TagView).isViewSelected) {
                    selectedPositions.add(i)
                }
            }
            return selectedPositions
        }
    val selectedTagViewText: List<String>
        /**
         * Return selected TagView text
         *
         * @return list of selected tag text
         */
        get() {
            val selectedTagText: MutableList<String> = ArrayList()
            for (i in mChildViews!!.indices) {
                val tagView = mChildViews!![i] as TagView
                if (tagView.isViewSelected) {
                    selectedTagText.add(tagView.text!!)
                }
            }
            return selectedTagText
        }

    /**
     * Return number of child tags
     *
     * @return size
     */
    fun size(): Int {
        return mChildViews!!.size
    }

    /**
     * Get TagView text.
     *
     * @param position
     * @return
     */
    fun getTagText(position: Int): String {
        return (mChildViews!![position] as TagView).text!!
    }

    var tags: List<String>?
        /**
         * Get a string list for all tags in TagContainerLayout.
         *
         * @return
         */
        get() {
            val tmpList: MutableList<String> = ArrayList()
            for (view in mChildViews!!) {
                if (view is TagView) {
                    tmpList.add(view.text!!)
                }
            }
            return tmpList
        }
        /**
         * Set tags
         *
         * @param tags
         */
        set(tags) {
            mTags = tags
            onSetTag()
        }

    /**
     * Set vertical interval
     *
     * @param interval
     */
    fun setVerticalInterval(interval: Float) {
        verticalInterval = dp2px(context, interval) as Int
        postInvalidate()
    }

    /**
     * Set horizontal interval.
     *
     * @param interval
     */
    fun setHorizontalInterval(interval: Float) {
        horizontalInterval = dp2px(context, interval) as Int
        postInvalidate()
    }

    /**
     * Get TagContainerLayout background color.
     *
     * @return
     */
    fun getBackgroundColor(): Int {
        return mBackgroundColor
    }

    /**
     * Set TagContainerLayout background color.
     *
     * @param color
     */
    override fun setBackgroundColor(color: Int) {
        mBackgroundColor = color
    }

    var maxLines: Int
        /**
         * Get TagContainerLayout's max lines
         *
         * @return maxLines
         */
        get() = mMaxLines
        /**
         * Set max line count for TagContainerLayout
         *
         * @param maxLines max line count
         */
        set(maxLines) {
            mMaxLines = maxLines
            postInvalidate()
        }
    var tagMaxLength: Int
        /**
         * Get TagView max length.
         *
         * @return
         */
        get() = mTagMaxLength
        /**
         * Set the TagView text max length(must greater or equal to 3).
         *
         * @param maxLength
         */
        set(maxLength) {
            mTagMaxLength = if (maxLength < TAG_MIN_LENGTH) TAG_MIN_LENGTH else maxLength
        }
    var tagHorizontalPadding: Int
        /**
         * Get TagView horizontal padding.
         *
         * @return
         */
        get() = mTagHorizontalPadding
        /**
         * Set TagView horizontal padding.
         *
         * @param padding
         */
        set(padding) {
            val ceilWidth = ceilTagBorderWidth()
            mTagHorizontalPadding = if (padding < ceilWidth) ceilWidth else padding
        }
    var tagVerticalPadding: Int
        /**
         * Get TagView vertical padding.
         *
         * @return
         */
        get() = mTagVerticalPadding
        /**
         * Set TagView vertical padding.
         *
         * @param padding
         */
        set(padding) {
            val ceilWidth = ceilTagBorderWidth()
            mTagVerticalPadding = if (padding < ceilWidth) ceilWidth else padding
        }

    /**
     * Get TagView in specified position.
     *
     * @param position the position of the TagView
     * @return
     */
    fun getTagView(position: Int): TagView {
        if (position < 0 || position >= mChildViews!!.size) {
            throw RuntimeException("Illegal position!")
        }
        return mChildViews!![position] as TagView
    }

    companion object {
        /**
         * Default interval(dp)
         */
        private const val DEFAULT_INTERVAL = 5f

        /**
         * Default tag min length
         */
        private const val TAG_MIN_LENGTH = 3
    }
}

