package com.opalab.littlecolor;

public class ColorGradient {

    public enum ColorPosition {
        BOTTOM, TOP, LEFT, RIGHT
    }

    private String[] initial_colors = new String[] {"#FFFFFF","#000000","#FFFFFF","#000000"};

    private float width = 0;
    private float height = 0;
    private String[] bound_colors = null;

    public ColorGradient(float width, float height, String[]... new_bound_colors){
        this.width = width;
        this.height = height;
        this.bound_colors = new_bound_colors.length == 1 ? new_bound_colors[0] : this.initial_colors;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public String getInitialColorForPosition(ColorPosition colorPosition){
        return bound_colors[colorPosition.ordinal()];
    }

    public String getColor(float x, float y){
        float width_ratio = x/this.width;
        float height_ratio = y/this.height;

        int new_r, new_g, new_b = 0;

        // Red component  --> BOTTOM <-> TOP
        int c_r_b = ColorConverter.fromStringToIntegers(getInitialColorForPosition(ColorPosition.BOTTOM))[0];
        int c_r_t = ColorConverter.fromStringToIntegers(getInitialColorForPosition(ColorPosition.TOP))[0];
        double new_r_f = Math.ceil(c_r_b + (c_r_t-c_r_b)*height_ratio);

        // Green component --> LEFT <-> RIGHT
        int c_g_l = ColorConverter.fromStringToIntegers(getInitialColorForPosition(ColorPosition.LEFT))[1];
        int c_g_r = ColorConverter.fromStringToIntegers(getInitialColorForPosition(ColorPosition.RIGHT))[1];
        double new_g_f = Math.ceil(c_r_b + (c_r_t-c_r_b)*width_ratio);

        // Blue component --> BOTTOM <-> TOP
        double new_b_f = 30;
        double max_x = getHeight();
        double max_y = getWidth();

        // distance between cursor and edge
        int c_b_l = ColorConverter.fromStringToIntegers(getInitialColorForPosition(ColorPosition.LEFT))[2];
        int c_b_r = ColorConverter.fromStringToIntegers(getInitialColorForPosition(ColorPosition.RIGHT))[2];

        new_b_f = Math.sqrt( (max_x-x)*(max_x-x) + (max_y-y)*(max_y-y)  );
        double total_distance = Math.sqrt( max_x*max_x + max_y*max_y  );
        float n_ratio = (float)new_b_f/(float)total_distance;

        new_b_f = Math.ceil(c_b_l + (c_b_r-c_b_l)*n_ratio);


        return ColorConverter.fromIntegersToString(new int[] {
            (int)new_r_f,
            (int)new_g_f,
            (int)new_b_f
        });
    }

}

