package com.amh.util;

import com.amh.constants.PetAssetName;

import java.awt.image.BufferedImage;

import static com.amh.common.CommonData.*;

public class AssetStore {

    private static final BufferedImage corgiWithTail;
    private static final BufferedImage corgiWithNoTail;
    private static final BufferedImage corgiTriColorWithTail;
    private static final BufferedImage corgiTriColorWithNoTail;
    private static final BufferedImage cat;

    static {
            corgiWithTail = ImageLoader.GetResourceImage(CORGI_WITH_TAIL_IMG);
            corgiWithNoTail = ImageLoader.GetResourceImage(CORGI_WITH_NO_TAIL_IMG);
            corgiTriColorWithTail = ImageLoader.GetResourceImage(CORGI_TRICOLOR_WITH_TAIL_IMG);
            corgiTriColorWithNoTail = ImageLoader.GetResourceImage(CORGI_TRICOLOR_WITH_NO_TAIL_IMG);
            cat = ImageLoader.GetResourceImage(CAT);
    }

    public static BufferedImage[][] GetCorgiAnimations(PetAssetName petAssetName){
        BufferedImage[][] animations;
        BufferedImage rawSprite = checkCorgiAsset(petAssetName);

        if(petAssetName== PetAssetName.CORGI_TRI_COLOR_WITH_NO_TAIL){
           animations = new BufferedImage[16][13];
        }else{
            animations = new BufferedImage[8][11];
        }

        for (int y = 0; y < animations.length; y++) {
            for (int x = 0; x < animations[y].length; x++) {
                animations[y][x] = rawSprite.getSubimage( (x * 64) + 96, y * 64, 64, 64);
            }
        }
        return animations;
    }

    public static BufferedImage[][] GetCatAnimations(PetAssetName petAssetName) {
        BufferedImage[][] animations = new BufferedImage[10][8];
        BufferedImage rawSprite = checkCorgiAsset(petAssetName);

        for (int y = 0; y < animations.length; y++) {
            for (int x = 0; x < animations[y].length; x++) {
                animations[y][x] = rawSprite.getSubimage( x * 32 , y * 32, 32, 32);
            }
        }
        return animations;
    }

    private static BufferedImage checkCorgiAsset(PetAssetName petAssetName) {
        switch (petAssetName) {
            case CORGI_WITH_NO_TAIL: return corgiWithNoTail;
            case CORGI_TRI_COLOR_WITH_TAIL: return corgiTriColorWithTail;
            case CORGI_TRI_COLOR_WITH_NO_TAIL: return corgiTriColorWithNoTail;
            case CAT : return cat;
            default: return corgiWithTail;
        }
    }
}
