package ablancoca.verticalpageindicatorsample;

import android.support.v4.view.PagerAdapter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ablancoca.verticalpageindicator.VerticalPageIndicator;
import fr.castorflex.android.verticalviewpager.VerticalViewPager;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VerticalViewPager pager = (VerticalViewPager) findViewById(R.id.verticalviewpager);
        VerticalPageIndicator indicator = (VerticalPageIndicator) findViewById(R.id.indicator);

        DummyAdapter adapter = new DummyAdapter();
        pager.setAdapter(adapter);

        //Call setViewPager after set the pager adapter
        indicator.setVerticalViewPager(pager);
    }


    class DummyAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View v = View.inflate(MainActivity.this,R.layout.item_page,null);
            TextView textView = (TextView) v.findViewById(R.id.text);
            textView.setText("Hello! I'm the page " + String.valueOf(position + 1));
            container.addView(v);
            return v;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
