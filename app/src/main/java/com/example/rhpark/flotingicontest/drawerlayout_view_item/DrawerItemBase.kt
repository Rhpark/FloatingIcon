package com.example.rhpark.flotingicontest.drawerlayout_view_item

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout

/**
 * Created by Rhpark on 2017-11-19.
 */
open class DrawerItemBase(context: Context): LinearLayout(context)
{
    open lateinit var view: View

    open var lf = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater


}