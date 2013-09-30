package com.opalab.littlecolor.rtest;

import com.opalab.littlecolor.ColorGradient;

import junit.framework.Assert;

import roboguice.test.RobolectricRoboTestRunner;

import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import org.junit.runner.RunWith;
import org.junit.Before;
import org.junit.Test;

import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.lang.Float;

@RunWith(RobolectricTestRunner.class)
public class ColorGradientTest {

    @Before
    public void setUp() {
    }

    @Test
    public void constructor(){
        ColorGradient gradient = new ColorGradient(100, 100);

        Assert.assertEquals(new Float(100), gradient.getWidth());
        Assert.assertEquals(new Float(100), gradient.getHeight());
    }

    @Test
    public void initial_color_for_position(){
        ColorGradient gradient = new ColorGradient(100, 100);

        Assert.assertEquals("#FFFFFF", gradient.getInitialColorForPosition(ColorGradient.ColorPosition.BOTTOM));
    }

    @Test
    public void initial_color_for_position_with_some_colors(){
        String[] colors = {"#CCCCCC","#000000","#FFFFFF","#000000"};
        ColorGradient gradient = new ColorGradient(100, 100, colors);

        Assert.assertEquals("#CCCCCC", gradient.getInitialColorForPosition(ColorGradient.ColorPosition.BOTTOM));
    }

    @Test
    public void get_color_for_20x20_position(){
        ColorGradient gradient = new ColorGradient(1000, 1000);
        String color = gradient.getColor(200,200);

        Assert.assertNotNull(color);
        Assert.assertEquals("#CCCC34", color);
    }

}