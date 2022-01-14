package org.digitall.common.geo.mapping.osm.tilesources;

import org.digitall.common.geo.mapping.osm.interfaces.TileSource;

public class TMSTileSource extends AbstractOsmTileSource {
    private int maxZoom;

    public TMSTileSource(String name, String url, int maxZoom) {
        super(name, url);
        this.maxZoom = maxZoom;
    }

    @Override
    public int getMaxZoom() {
        return (maxZoom == 0) ? super.getMaxZoom() : maxZoom;
    }

    public TileUpdate getTileUpdate() {
        return TileSource.TileUpdate.IfNoneMatch;
    }
}
