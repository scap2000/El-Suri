package tim.prune.gui.map;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;

import tim.prune.GpsPrune;

/**
 * Class to asynchronously download a tile from a url
 * and populate an Image object with the contents
 */
public class TileDownloader implements Runnable
{
	private MapTileManager _manager = null;
	private URL _url = null;
	private int _layer = 0;
	private int _x = 0, _y = 0;
	private int _zoom = 0;
	/** Hashset of all blocked / 404 tiles to avoid requesting them again */
	private static final HashSet<String> BLOCKED_URLS = new HashSet<String>();
	/** Hashset of all currently loading tiles to avoid requesting them again */
	private static final HashSet<String> LOADING_URLS = new HashSet<String>();


	/**
	 * Constructor (private)
	 * @param inManager parent manager for callback
	 * @param inUrl URL to load
	 * @param inLayer layer index from 0
	 * @param inX x coordinate of tile
	 * @param inY y coordinate of tile
	 * @param inZoom zoom level
	 */
	private TileDownloader(MapTileManager inManager, URL inUrl, int inLayer, int inX, int inY, int inZoom)
	{
		_manager = inManager;
		_url = inUrl;
		_layer = inLayer;
		_x = inX; _y = inY;
		_zoom = inZoom;
	}

	/**
	 * Trigger a download in a new thread
	 * @param inManager manager to callback when image is loaded
	 * @param inUrl URL to load
	 * @param inLayer layer index from 0
	 * @param inX x coordinate of tile
	 * @param inY y coordinate of tile
	 * @param inZoom current zoom level
	 */
	public static synchronized void triggerLoad(MapTileManager inManager, URL inUrl, int inLayer,
		int inX, int inY, int inZoom)
	{
		if (inManager != null && inUrl != null)
		{
			String url = inUrl.toString();
			if (!BLOCKED_URLS.contains(url) && !LOADING_URLS.contains(url))
			{
				LOADING_URLS.add(url);
				new Thread(new TileDownloader(inManager, inUrl, inLayer, inX, inY, inZoom)).start();
			}
		}
	}

	/**
	 * Run method, called in separate thread
	 */
	public void run()
	{
		InputStream in = null;
		try
		{
			// Set http user agent on connection
			URLConnection conn = _url.openConnection();
			conn.setRequestProperty("User-Agent", "GpsPrune v" + GpsPrune.VERSION_NUMBER);
			in = conn.getInputStream();
			int len = conn.getContentLength();
			if (len > 0)
			{
				byte[] data = new byte[len];
				int totalRead = 0;
				while (totalRead < len)
				{
					int numRead = in.read(data, totalRead, len-totalRead);
					totalRead += numRead;
				}
				Image tile = Toolkit.getDefaultToolkit().createImage(data);
				in.close();

				// Pass back to manager so it can be stored in its memory cache
				_manager.notifyImageLoaded(tile, _layer, _x, _y, _zoom);
			}
		}
		catch (IOException e)
		{
			System.err.println("IOE: " + e.getClass().getName() + " - " + e.getMessage());
			synchronized(this.getClass())
			{
				BLOCKED_URLS.add(_url.toString());
			}
			try {in.close();} catch (Exception e2) {}
		}
		LOADING_URLS.remove(_url.toString());
	}
}
