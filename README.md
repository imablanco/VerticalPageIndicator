# VerticalPageIndicator
A page indicator for castorflex's VerticalViewPager: https://github.com/castorflex/VerticalViewPager

#Usage
Just include the widget adjacent to the VerticalViewPager it represents.
```xml
<ablancoca.verticalpageindicator.VerticalPageIndicator
  android:id="@+id/indicator"
  android:layout_width="20dp"
  android:layout_height="match_parent"
  app:vpiselectedcolor="#000000"
  app:vpiunselectedcolor="#FFFFFF"
  app:vpisize="10"/>
```

You can set the attributes in code too:

```java
  setSelectedColor(int color);
  
  setUnselectedColor(int color);
  
  setSize(int size);

```

In order to use VerticalPageIndicator correctly, you should call 
```java 
  setVerticalViewPager(VerticalViewPager pager);
```
after settign the VerticalViewPager adapter;

#Examples

```java
  VerticalViewPager pager = (VerticalViewPager) findViewById(R.id.verticalviewpager);
  VerticalPageIndicator indicator = (VerticalPageIndicator) findViewById(R.id.indicator);

  DummyAdapter adapter = new DummyAdapter();
  pager.setAdapter(adapter);

  indicator.setVerticalViewPager(pager);
```

You can see the example at VerticalPageIndicatorSample

#License

This project has a MIT License, so feel free to use as you want


