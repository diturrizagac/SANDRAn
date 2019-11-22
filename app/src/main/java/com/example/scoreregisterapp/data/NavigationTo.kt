package com.diturrizaga.easypay.util

import android.content.Context
import android.content.Intent
import android.os.Bundle


object NavigationTo {
   fun goTo(activity: Class<*>, context: Context, id: String?) {
      val intent = Intent(context,activity)
      intent.putExtra("userId", id)
      context.startActivity(intent)
   }

   fun goTo(activity: Class<*>, context: Context, id: String?, role: String?) {
      val intent = Intent(context,activity)
      intent.putExtra("userId", id)
      intent.putExtra("userRole", role)
      context.startActivity(intent)
   }

   fun goTo(activity: Class<*>, context: Context) {
      val intent = Intent(context, activity)
      context.startActivity(intent)
   }
}