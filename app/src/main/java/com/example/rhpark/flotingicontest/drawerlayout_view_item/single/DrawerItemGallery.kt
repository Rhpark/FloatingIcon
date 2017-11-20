package com.example.rhpark.fticon.drawerlayout_views.single

import android.content.Context
import com.example.rhpark.flotingicontest.R
import com.example.rhpark.flotingicontest.drawerlayout_view_item.DrawerItemBase

/**
 * Created by Rhpark on 2017-11-19.
 */

class DrawerItemGallery(context:Context):DrawerItemBase(context)
{
    init {
        view = lf.inflate(R.layout.drawer_item_view_gallery, this,false)
    }
}
