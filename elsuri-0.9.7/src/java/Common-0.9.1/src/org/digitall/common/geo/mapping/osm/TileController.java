package org.digitall.common.geo.mapping.osm;

import org.digitall.common.geo.mapping.osm.interfaces.TileCache;
import org.digitall.common.geo.mapping.osm.interfaces.TileLoader;
import org.digitall.common.geo.mapping.osm.interfaces.TileLoaderListener;
import org.digitall.common.geo.mapping.osm.interfaces.TileSource;
import org.digitall.common.geo.mapping.osm.tilesources.OsmTileSource;
import org.digitall.common.geo.mapping.osm.tilesources.OsmTileSource.Mapnik;

public class TileController {
    protected TileLoader tileLoader;
    protected TileCache tileCache;
    protected TileSource tileSource;

    JobDispatcher jobDispatcher;

    public TileController(TileSource source, TileCache tileCache, TileLoaderListener listener) {
        tileSource = new OsmTileSource.Mapnik();
        tileLoader = new OsmTileLoader(listener);
        this.tileCache = tileCache;
        jobDispatcher = JobDispatcher.getInstance();
    }

    /**
     * retrieves a tile from the cache. If the tile is not present in the cache
     * a load job is added to the working queue of {@link org.digitall.common.geo.mapping.osm.JobDispatcher.JobThread}.
     *
     * @param tilex
     * @param tiley
     * @param zoom
     * @return specified tile from the cache or <code>null</code> if the tile
     * was not found in the cache.
     */
    public Tile getTile(int tilex, int tiley, int zoom) {
        int max = (1 << zoom);
        if (tilex < 0 || tilex >= max || tiley < 0 || tiley >= max)
            return null;
        Tile tile = tileCache.getTile(tileSource, tilex, tiley, zoom);
        if (tile == null) {
            tile = new Tile(tileSource, tilex, tiley, zoom);
            tileCache.addTile(tile);
            tile.loadPlaceholderFromCache(tileCache);
        }
        if (!tile.isLoaded()) {
            jobDispatcher.addJob(tileLoader.createTileLoaderJob(tileSource, tilex, tiley, zoom));
        }
        return tile;
    }

    public TileCache getTileCache() {
        return tileCache;
    }

    public void setTileCache(TileCache tileCache) {
        this.tileCache = tileCache;
    }

    public TileLoader getTileLoader() {
        return tileLoader;
    }

    public void setTileLoader(TileLoader tileLoader) {
        this.tileLoader = tileLoader;
    }

    public TileSource getTileLayerSource() {
        return tileSource;
    }

    public TileSource getTileSource() {
        return tileSource;
    }

    public void setTileSource(TileSource tileSource) {
        this.tileSource = tileSource;
    }

    /**
     *
     */
    public void cancelOutstandingJobs() {
        jobDispatcher.cancelOutstandingJobs();
    }
}
