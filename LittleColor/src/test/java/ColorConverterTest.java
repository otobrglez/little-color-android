package com.opalab.littlecolor.rtest;

import com.opalab.littlecolor.ColorConverter;

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
public class ColorConverterTest {

    @Test
    public void from_string_to_integers(){
        int colors[] = ColorConverter.fromStringToIntegers("#FF00FF");
        Assert.assertEquals(255,colors[0]);
    }

    @Test
    public void from_integers_to_string(){
        String color = ColorConverter.fromIntegersToString(new int[]{255, 255, 255});
        Assert.assertEquals("#FFFFFF",color);
    }

    @Test
    public void from_integers_to_string_black(){
        String color = ColorConverter.fromIntegersToString(new int[]{0, 0, 0});
        Assert.assertEquals("#000000",color);
    }
}