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

package de.chaosdorf.meteroid.imageloader;

import android.content.Context;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

public class FileCache
{
	private final File cacheDir;

	public FileCache(final Context context)
	{
		cacheDir = context.getCacheDir();
		if (cacheDir != null && !cacheDir.exists())
		{
			if (!cacheDir.mkdirs())
			{
				throw new RuntimeException("Could not create cache directory!");
			}
		}
	}

	public void clear()
	{
		final File[] cachedFiles = cacheDir.listFiles();
		if (cachedFiles == null)
		{
			return;
		}
		for (File file : cachedFiles)
		{
			if (!file.delete())
			{
				throw new RuntimeException("Could not delete cached file " + file.getName());
			}
		}
	}

	public File getFile(final URL url)
	{
		return new File(cacheDir, createFileNameFromURL(url));
	}

	private String createFileNameFromURL(final URL url)
	{
		try
		{
			final String filename = url.toString().replaceFirst(url.getProtocol(), "").substring(1);
			return URLEncoder.encode(filename.startsWith("//") ? filename.substring(2) : filename, "UTF-8");
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		return String.valueOf(url.hashCode());
	}
}
