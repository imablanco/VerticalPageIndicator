package ablancoca.verticalpageindicator;

/**
 * Created by ablancoca on 20/3/15.
 * A page indicator for castorflex's VerticalViewPager
 */
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import fr.castorflex.android.verticalviewpager.VerticalViewPager;


public class VerticalPageIndicator extends LinearLayout {

    private int selectedColor, unselectedColor;
    private int childCount;
    private LinearLayout container;
    private int size;

    public VerticalPageIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.VerticalPageIndicator,
                0, 0);

        try {
            selectedColor = a.getColor(R.styleable.VerticalPageIndicator_vpiselectedcolor,getResources().getColor(R.color.button_material_dark));
            unselectedColor = a.getColor(R.styleable.VerticalPageIndicator_vpiunselectedcolor, getResources().getColor(R.color.button_material_light));
            size = a.getInteger(R.styleable.VerticalPageIndicator_vpisize,10);
        } finally {
            a.recycle();
        }




    }

    /**
     * Init the indicator view based on the VerticalViewPager item count
     * @param pager
     */
    public void setVerticalViewPager(final VerticalViewPager pager){
        pager.post(new Runnable() {
            @Override
            public void run() {
                childCount = pager.getAdapter().getCount();
                removeAllViews();
                init(pager);

            }
        });




    }


    /**
     * Init the rendering of the indicator view
     * @param pager
     */
    private void init(VerticalViewPager pager) {
        container = new LinearLayout(getContext());
        LinearLayout.LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        container.setOrientation(VERTICAL);
        container.setLayoutParams(params);
        addView(container);

        for(int i = 0; i<childCount;i++){
            View v = new View(getContext());
            LinearLayout.LayoutParams viewParams = new LayoutParams(convertDpToPixel(size),convertDpToPixel(size));
            viewParams.topMargin=size/2;
            viewParams.bottomMargin = size/2;
            v.setBackgroundResource(R.drawable.indicator_shape);
            v.setLayoutParams(viewParams);

            container.addView(v);
        }

        setCurrentItem(pager.getCurrentItem());

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                setCurrentItem(position);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    /**
     * Displays the current indicator index
     * @param pos
     */
    private void setCurrentItem(int pos){
        for(int i =0 ; i<container.getChildCount();i++){
            if(i == pos){
               setSelected(container.getChildAt(i));
            }
            else{
                setUnselected(container.getChildAt(i));

            }
        }
    }

    public void setSelectedColor(int selectedColor) {
        this.selectedColor = selectedColor;
    }

    public void setUnselectedColor(int unselectedColor) {
        this.unselectedColor = unselectedColor;
    }

    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Sets the view selected
     * @param v
     */
    private void setSelected(View v){
        ((GradientDrawable)v.getBackground()).setColor(selectedColor);
    }

    /**
     * Sets the view unselected
     * @param v
     */
    private void setUnselected(View v){
        ((GradientDrawable)v.getBackground()).setColor(unselectedColor);
    }

    /**
     * Converts passed dp into pixel depending on screen resolution
     * @param dp
     * @return
     */
    private int convertDpToPixel(int dp){
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int px = dp * (metrics.densityDpi / 160);
        return px;
    }
}
