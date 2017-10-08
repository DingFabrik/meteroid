/*******************************************************************************
 * The MIT License (MIT)
 *
 * Copyright (c) 2013-2016 Chaosdorf e.V.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 ******************************************************************************/

package de.chaosdorf.meteroid.util;

import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;

public class MenuUtility
{
	public static void setChecked(final Menu menu, final int itemID, final boolean status)
	{
		final MenuItem menuItem = menu.findItem(itemID);
		if (menuItem != null)
		{
			menuItem.setChecked(status);
		}
	}

	public static boolean onClickMultiUserMode(final Activity activity, final MenuItem item)
	{
		final boolean multiUserMode = Utility.toggleMultiUserMode(activity);
		item.setChecked(multiUserMode);
		if (multiUserMode)
		{
			Config config = new Config(activity);
			config.userID = config.NO_USER_ID;
			config.save();
		}
		return multiUserMode;
	}
}
