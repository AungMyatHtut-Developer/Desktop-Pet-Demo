package com.amh.util;

import com.amh.constants.CorgiAssetName;

import java.awt.image.BufferedImage;

import static com.amh.common.CommonData.*;

public class AssetStore {

    private static final BufferedImage corgiWithTail;
    private static final BufferedImage corgiWithNoTail;
    private static final BufferedImage corgiTriColorWithTail;
    private static final BufferedImage corgiTriColorWithNoTail;

    static {
            corgiWithTail = ImageLoader.GetResourceImage(CORGI_WITH_TAIL_IMG);
            corgiWithNoTail = ImageLoader.GetResourceImage(CORGI_WITH_NO_TAIL_IMG);
            corgiTriColorWithTail = ImageLoader.GetResourceImage(CORGI_TRICOLOR_WITH_TAIL_IMG);
            corgiTriColorWithNoTail = ImageLoader.GetResourceImage(CORGI_TRICOLOR_WITH_NO_TAIL_IMG);
    }

    public static BufferedImage[][] GetCorgiAnimations(CorgiAssetName corgiAssetName){
        BufferedImage[][] animations = new BufferedImage[8][11];
        BufferedImage rawSprite = checkCorgiAsset(corgiAssetName);

        for (int y = 0; y < animations.length; y++) {
            for (int x = 0; x < animations[y].length; x++) {
                animations[y][x] = rawSprite.getSubimage( (x * 64) + 96, y * 64, 64, 64);
            }
        }
        return animations;
    }

    private static BufferedImage checkCorgiAsset(CorgiAssetName corgiAssetName) {
        switch (corgiAssetName) {
            case CORGI_WITH_NO_TAIL: return corgiWithNoTail;
            case CORGI_TRI_COLOR_WITH_TAIL: return corgiTriColorWithTail;
            case CORGI_TRI_COLOR_WITH_NO_TAIL: return corgiTriColorWithNoTail;
            default: return corgiWithTail;
        }

    }
}
