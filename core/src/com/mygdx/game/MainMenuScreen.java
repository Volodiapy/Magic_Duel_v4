package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;

public class MainMenuScreen implements Screen {
    final Drop game;
    private TextureAtlas.AtlasRegion uphealthtexture;
    private TextureAtlas.AtlasRegion upmanatexture;
    private TextureAtlas.AtlasRegion upattacktexture;
    private TextureAtlas.AtlasRegion updeftexture;
    private TextureAtlas.AtlasRegion upcontroltexture;

    BitmapFont thealth, tmana, tattack, tdef, tcontrol, tcoin;

    private Rectangle uphealth;
    private Rectangle upmana;
    private Rectangle upattack;
    private Rectangle updef;
    private Rectangle upcontrol;
    private Rectangle exit;
    BitmapFont dialog;
    private TextureAtlas atlas;
    private  Rectangle fon, buttonstart, buttondialog;
    private SpriteBatch fonsprite, buttonstartsprite, buttodialogsprite, uphealthsprite, upmanasprite, upattacksprite, updefsprite, upcontrolsprite, exitsprite;
   TextureAtlas.AtlasRegion fontexture, buttonstarttexture, buttondialogtexture, exitteture;

    OrthographicCamera camera;
    Vector3 touchPos;
    BeautyTextRender btr = new BeautyTextRender();

    String sendtorender = "";
    int s = 1;
    private long time;
    public MainMenuScreen(Drop gam, int ford) {
        this.game = gam;
        this.s = ford;
        btr.init();
        camera = new OrthographicCamera();
        camera.setToOrtho(false,800,480);
        dialog = new BitmapFont();
        touchPos = new Vector3();

        atlas = new TextureAtlas(Gdx.files.internal("atlas.atlas"));
        fontexture =  atlas.findRegion("startsklep");
        buttonstarttexture =  atlas.findRegion("startbutton");
        buttondialogtexture =  atlas.findRegion("darkball1");
        uphealthtexture = atlas.findRegion("plus");
        upmanatexture = atlas.findRegion("plus");
        upattacktexture = atlas.findRegion("plus");
        updeftexture = atlas.findRegion("plus");
        upcontroltexture = atlas.findRegion("plus");
        exitteture = atlas.findRegion("plus");

        thealth = new BitmapFont();
        thealth.setColor(Color.RED);
        tmana = new BitmapFont();
        tmana.setColor(Color.BLUE);
        tattack = new BitmapFont();
        tattack.setColor(Color.CORAL);
        tdef = new BitmapFont();
        tdef.setColor(Color.OLIVE);
        tcontrol = new BitmapFont();
        tcontrol.setColor(Color.PINK);
        tcoin = new BitmapFont();
        tcoin.setColor(Color.YELLOW);

        fonsprite = new SpriteBatch();
        fonsprite.setProjectionMatrix(camera.combined);
        buttonstartsprite = new SpriteBatch();
        buttonstartsprite.setProjectionMatrix(camera.combined);
        buttodialogsprite = new SpriteBatch();
        buttodialogsprite.setProjectionMatrix(camera.combined);

        uphealthsprite = new SpriteBatch();
        uphealthsprite.setProjectionMatrix(camera.combined);
        upmanasprite = new SpriteBatch();
        upmanasprite.setProjectionMatrix(camera.combined);
        upattacksprite = new SpriteBatch();
        upattacksprite.setProjectionMatrix(camera.combined);
        updefsprite = new SpriteBatch();
        updefsprite.setProjectionMatrix(camera.combined);
        upcontrolsprite = new SpriteBatch();
        upcontrolsprite.setProjectionMatrix(camera.combined);
        exitsprite = new SpriteBatch();
        exitsprite.setProjectionMatrix(camera.combined);

        fon = new Rectangle();
        fon.x = 0;
        fon.y = 0;
        fon.width = 800;
        fon.height = 480;

        buttonstart = new Rectangle();
        buttonstart.x = fon.width/2 + 25;
        buttonstart.y = fon.height/2 - 50;
        buttonstart.width = 200;
        buttonstart.height = 100;

        buttondialog = new Rectangle();
        buttondialog.x = 100;
        buttondialog.y = 300;
        buttondialog.width = 50;
        buttondialog.height = 50;

        uphealth = new Rectangle();
        uphealth.x = 350;
        uphealth.y = 350;
        uphealth.width = 25;
        uphealth.height = 25;

        upmana = new Rectangle();
        upmana.x = 400;
        upmana.y = 350;
        upmana.width = 25;
        upmana.height = 25;

        upattack = new Rectangle();
        upattack.x = 450;
        upattack.y = 350;
        upattack.width = 25;
        upattack.height = 25;

        updef = new Rectangle();
        updef.x = 500;
        updef.y = 350;
        updef.width = 25;
        updef.height = 25;

        upcontrol = new Rectangle();
        upcontrol.x = 550;
        upcontrol.y = 350;
        upcontrol.width = 25;
        upcontrol.height = 25;

        exit = new Rectangle();
        exit.x = 0;
        exit.y = 430;
        exit.width = 50;
        exit.height = 50;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        camera.update();

        fonsprite.begin();
        fonsprite.draw(fontexture, fon.x, fon.y, fon.width,fon.height);
        sendtorender = btr.retstring(s, sendtorender);
        dialog.draw(fonsprite, sendtorender, 150, 300);
        tcoin.draw(fonsprite, String.valueOf(game.coin), 300, 375);
        fonsprite.end();
        buttonstartsprite.begin();
        buttonstartsprite.draw(buttonstarttexture, buttonstart.x, buttonstart.y, buttonstart.width,buttonstart.height);
        buttonstartsprite.end();
        buttodialogsprite.begin();
        buttodialogsprite.draw(buttondialogtexture, buttondialog.x, buttondialog.y, buttondialog.width,buttondialog.height);
        buttodialogsprite.end();
        uphealthsprite.begin();
        uphealthsprite.draw(uphealthtexture, uphealth.x, uphealth.y, uphealth.width,uphealth.height);
        thealth.draw(uphealthsprite, String.valueOf(game.maxhealth), uphealth.x + uphealth.width + 5, uphealth.y + uphealth.height);
        uphealthsprite.end();
        upmanasprite.begin();
        upmanasprite.draw(upmanatexture, upmana.x, upmana.y, upmana.width,upmana.height);
        tmana.draw(upmanasprite, String.valueOf(game.maxmana), upmana.x + upmana.width + 5, upmana.y + upmana.height);
        upmanasprite.end();
        upattacksprite.begin();
        upattacksprite.draw(upattacktexture, upattack.x, upattack.y, upattack.width,upattack.height);
        tattack.draw(upattacksprite, String.valueOf(game.modattack), upattack.x + upattack.width + 5, upattack.y + upattack.height);
        upattacksprite.end();
        updefsprite.begin();
        updefsprite.draw(updeftexture, updef.x, updef.y, updef.width,updef.height);
        tdef.draw(updefsprite, String.valueOf(game.moddef), updef.x + updef.width + 5, updef.y + updef.height);
        updefsprite.end();
        upcontrolsprite.begin();
        upcontrolsprite.draw(upcontroltexture, upcontrol.x, upcontrol.y, upcontrol.width,upcontrol.height);
        tcontrol.draw(upcontrolsprite, String.valueOf(game.modcontrol), upcontrol.x + upcontrol.width + 5, upcontrol.y + upcontrol.height);
        upcontrolsprite.end();
        exitsprite.begin();
        exitsprite.draw(exitteture, exit.x, exit.y, exit.width, exit.height);
        exitsprite.end();
        if(Gdx.input.isTouched()){
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            if(buttonstart.contains(touchPos.x, touchPos.y)){
                game.setScreen(new GameScreen(game));
                dispose();
            }
            if(buttondialog.contains(touchPos.x, touchPos.y)){
                if ((TimeUtils.nanoTime() - time) > 400000000){
                    time = TimeUtils.nanoTime();
                    sendtorender = "";
                    s += 1;
                    btr.newtext();

                }

            }
            if(uphealth.contains(touchPos.x, touchPos.y)){
                if(game.coin >= game.maxhealth/5){
                    game.coin -= game.maxhealth/5;
                }
            }
            if(upmana.contains(touchPos.x, touchPos.y)){
                if(game.coin >= game.maxmana/5){
                    game.coin -= game.maxmana/5;
                }
            }
            if(upattack.contains(touchPos.x, touchPos.y)){
                if(game.coin >= (game.modattack+1)* 5){
                    game.coin -= (game.modattack+1)* 5;
                }
            }
            if(updef.contains(touchPos.x, touchPos.y)){
                if(game.coin >= (game.moddef+1)* 5){
                    game.coin -= (game.moddef+1)* 5;
                }
            }
            if(upcontrol.contains(touchPos.x, touchPos.y)){
                if(game.coin >= (game.modcontrol+1)* 5){
                    game.coin -= (game.modcontrol+1)* 5;
                }
            }
            if(exit.contains(touchPos.x, touchPos.y)){
                Gdx.app.exit();
            }

        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
