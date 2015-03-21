package ablancoca.verticalpageindicatorsample;

import android.support.v4.view.PagerAdapter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ablancoca.verticalpageindicator.VerticalPageIndicator;
import fr.castorflex.android.verticalviewpager.VerticalViewPager;


public class MainActivity extends ActionBarActivity {

    private int dummyCount = 3;
    private DummyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VerticalViewPager pager = (VerticalViewPager) findViewById(R.id.verticalviewpager);
        VerticalPageIndicator indicator = (VerticalPageIndicator) findViewById(R.id.indicator);

        adapter = new DummyAdapter();
        pager.setAdapter(adapter);

        indicator.setVerticalViewPager(pager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            dummyCount++;
            adapter.notifyDataSetChanged();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    class DummyAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return dummyCount;
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
