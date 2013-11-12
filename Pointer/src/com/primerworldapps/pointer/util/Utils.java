package com.primerworldapps.pointer.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class Utils {

	public static void visitUrl(Context context, String url) {
		Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
		context.startActivity(browserIntent);
	}
}
