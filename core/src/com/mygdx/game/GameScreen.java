package com.mygdx.game;

import static java.lang.Thread.sleep;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;


public class GameScreen implements Screen {

	private final AtlasRegion manabartexture;
	private Rectangle manabar;
	private BitmapFont congratulations;
	private AtlasRegion buff1texture;
	private AtlasRegion buff2texture;
	private AtlasRegion buff3texture;
	private SpriteBatch buff1sprite;
	private SpriteBatch buff2sprite;
	private SpriteBatch buff3sprite;
	private Rectangle buff1;
	private Rectangle buff2;
	private Rectangle buff3;

	@Override
	public void show() {

	}

	final Drop game;
	//private String[] whatspells = "1n2n3n4n5n6".split("n");
	String whatsend;
	String enemywhatsend;
	//private String[] enemywhatspells = "11n12n7n9n8n10".split("n");
	int maxhealth = 0, maxmana = 0, moddef = 0, modcontrol = 0, modattack = 0;
	int enemymaxhealth = 0, enemymaxmana = 0, enemymoddef = 0, enemymodcontrol = 0, enemymodattack = 0;

	private int health = 100;
	private int mana = 100;
	private int enemyhealth = 100;
	private int enemymana = 100;
	BitmapFont healthfont;
	BitmapFont manafont;
	BitmapFont modattackfont;
	BitmapFont moddeffont;
	BitmapFont modcontrolfont;
	BitmapFont damageview;
	BitmapFont enemydamageview;

	TextureAtlas atlas;
	//TextureRegion region;
	OrthographicCamera camera;
	SpriteBatch fonsprite;
	SpriteBatch heroparamssprite;
	SpriteBatch slot1sprite;
	SpriteBatch slot2sprite;
	SpriteBatch slot3sprite;
	SpriteBatch slot4sprite;
	SpriteBatch slot5sprite;
	SpriteBatch slot6sprite;
	SpriteBatch herosprite;
	SpriteBatch enemysprite;
	SpriteBatch spell1sprite;
	SpriteBatch spell2sprite;
	SpriteBatch spell3sprite;
	SpriteBatch spell4sprite;
	SpriteBatch spell5sprite;
	SpriteBatch spell6sprite;
	SpriteBatch enemyspell1sprite;
	SpriteBatch enemyspell2sprite;
	SpriteBatch enemyspell3sprite;
	SpriteBatch enemyspell4sprite;
	SpriteBatch enemyspell5sprite;
	SpriteBatch enemyspell6sprite;
	SpriteBatch manabarsprite;
	AtlasRegion fontexture;
	AtlasRegion heroparamstexture;
	AtlasRegion slottexture;
	AtlasRegion herotexture;
	AtlasRegion enemytexture;
	AtlasRegion pustotatexture;
	AtlasRegion spell1texture;
	AtlasRegion spell2texture;
	AtlasRegion spell3texture;
	AtlasRegion spell4texture;
	AtlasRegion spell5texture;
	AtlasRegion spell6texture;
	AtlasRegion enemyspell1texture;
	AtlasRegion enemyspell2texture;
	AtlasRegion enemyspell3texture;
	AtlasRegion enemyspell4texture;
	AtlasRegion enemyspell5texture;
	AtlasRegion enemyspell6texture;
	AtlasRegion[] texturesarr;
	AtlasRegion[] enemytexturesarr;
	Rectangle fon;
	Rectangle heroparams;
	Rectangle slot1;
	Rectangle slot2;
	Rectangle slot3;
	Rectangle slot4;
	Rectangle slot5;
	Rectangle slot6;
	Rectangle hero;
	Rectangle enemy;
	Rectangle spell1rect;
	Rectangle spell2rect;
	Rectangle spell3rect;
	Rectangle spell4rect;
	Rectangle spell5rect;
	Rectangle spell6rect;
	Rectangle enemyspell1rect;
	Rectangle enemyspell2rect;
	Rectangle enemyspell3rect;
	Rectangle enemyspell4rect;
	Rectangle enemyspell5rect;
	Rectangle enemyspell6rect;
	Rectangle[] slotrects;
	Vector3 touchPos;
	Spell spell1;
	Spell spell2;
	Spell spell3;
	Spell spell4;
	Spell spell5;
	Spell spell6;
	Spell[] spellsforslots;
	Spell enemyspell1;
	Spell enemyspell2;
	Spell enemyspell3;
	Spell enemyspell4;
	Spell enemyspell5;
	Spell enemyspell6;
	Spell[] enemyspellsforslots;
	EnemyShablon enemyshablon = new EnemyShablon();
	private boolean iscast = false;
	private boolean isspell1 = false;
	private boolean isspell2 = false;
	private boolean isspell3 = false;
	private boolean isspell4 = false;
	private boolean isspell5 = false;
	private boolean isspell6 = false;
	private int pause = 0;



	long time;
	int herofrme = 1;
	int width = 800;
	int height = 480;

	Spell[] enemytypes;
	Spell[] types;







	public int godice(int mi, int ma){
		return (int)(Math.random() * ma) + mi;
	}

	private void tic(){
		if(herofrme == 2){
			herotexture = atlas.findRegion("darkhero1");
			enemytexture = atlas.findRegion("greyadept1");
			herofrme = 1;
			if (mana < maxmana){
				mana += 1;
			}
			if(enemymana < enemymaxmana){
				enemymana += 1;
			}

		}
		else{
			herotexture = atlas.findRegion("darkhero2");
			enemytexture = atlas.findRegion("greyadept2");
			herofrme = 2;
		}

		time = TimeUtils.nanoTime();
	}
	public void endFail(){
		try {
			game.setbegin();
			pause = 2;
			sleep(1000);
			game.setScreen(new MainMenuScreen(game, 999));
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

	}
	public void endWin(){
		try {
			pause = 2;
			fontexture = atlas.findRegion("startsklep");
			if(game.stage < 4 & game.stage % 2 == 1){
				game.enemywhatspells[game.stage + 1] = String.valueOf(9+game.stage);

			}
			game.enemymaxhealth += godice(1, 5);;
			game.enemymaxmana += godice(5, 10);;
			game.enemymodattack += godice(0, 1);
			game.enemymoddef += godice(0, 1);
			game.enemymodcontrol += godice(0, 1);
			sleep(1);

		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

	}

	public GameScreen(final Drop gam) {
		this.game = gam;
		game.stage += 1;
		maxhealth = game.maxhealth; maxmana = game.maxmana; moddef = game.moddef; modcontrol = game.modcontrol; modattack = game.modattack;
		health = maxhealth;
		mana = maxmana;
		enemymaxhealth = game.enemymaxhealth; enemymaxmana = game.enemymaxmana; enemymoddef = game.enemymoddef; enemymodcontrol = game.enemymodcontrol; enemymodattack = game.enemymodattack;
		enemyhealth = enemymaxhealth;
		enemymana = enemymaxmana;

		atlas = new TextureAtlas(Gdx.files.internal("atlas.atlas"));

		camera = new OrthographicCamera();
		camera.setToOrtho(false,width,height);

		fonsprite = new SpriteBatch();
		heroparamssprite = new SpriteBatch();
		slot1sprite = new SpriteBatch();
		slot2sprite = new SpriteBatch();
		slot3sprite = new SpriteBatch();
		slot4sprite = new SpriteBatch();
		slot5sprite = new SpriteBatch();
		slot6sprite = new SpriteBatch();
		herosprite = new SpriteBatch();
		enemysprite = new SpriteBatch();
		spell1sprite = new SpriteBatch();
		spell2sprite = new SpriteBatch();
		spell3sprite = new SpriteBatch();
		spell4sprite = new SpriteBatch();
		spell5sprite = new SpriteBatch();
		spell6sprite = new SpriteBatch();
		enemyspell1sprite = new SpriteBatch();
		enemyspell2sprite = new SpriteBatch();
		enemyspell3sprite = new SpriteBatch();
		enemyspell4sprite = new SpriteBatch();
		enemyspell5sprite = new SpriteBatch();
		enemyspell6sprite = new SpriteBatch();
		manabarsprite = new SpriteBatch();
		buff1sprite = new SpriteBatch();
		buff2sprite = new SpriteBatch();
		buff3sprite = new SpriteBatch();

		types = new Spell[]{new Spell(), new Spell(), new DarkBall(), new DarkShield(), new DarkCurse(), new DarkTriangle(), new DarkLine(), new DarkShadow(), new LightBall(), new LightShield(), new LightHeal(), new LightPulling(), new LightLine(), new LightShadow()};

		spell1 = types[Integer.parseInt(game.whatspells[0])];
		spell2 = types[Integer.parseInt( game.whatspells[1])];
		spell3 = types[Integer.parseInt( game.whatspells[2])];
		spell4 = types[Integer.parseInt( game.whatspells[3])];
		spell5 = types[Integer.parseInt( game.whatspells[4])];
		spell6 = types[Integer.parseInt( game.whatspells[5])];

		enemytypes = new Spell[]{new Spell(), new Spell(), new EnemyDarkBall(), new EnemyDarkShield(), new EnemyDarkCurse(), new EnemyDarkTriangle(), new EnemyDarkLine(), new EnemyDarkShadow(), new EnemyLightBall(), new EnemylightShield(), new EnemylightHeal(), new EnemyLightPulling(), new EnemyLightLine(), new EnemyLightShadow()};

		enemyspell1 = enemytypes[Integer.parseInt( game.enemywhatspells[0])];
		enemyspell2 = enemytypes[Integer.parseInt( game.enemywhatspells[1])];
		enemyspell3 = enemytypes[Integer.parseInt( game.enemywhatspells[2])];
		enemyspell4 = enemytypes[Integer.parseInt( game.enemywhatspells[3])];
		enemyspell5 = enemytypes[Integer.parseInt( game.enemywhatspells[4])];
		enemyspell6 = enemytypes[Integer.parseInt( game.enemywhatspells[5])];

		spellsforslots = new Spell[]{spell1, spell2, spell3, spell4, spell5, spell6};
		enemyspellsforslots = new Spell[]{enemyspell1, enemyspell2, enemyspell3, enemyspell4, enemyspell5, enemyspell6};

		fontexture =  atlas.findRegion("fonsklep");
		heroparamstexture = atlas.findRegion("darkcurse1");
		slottexture =  atlas.findRegion("slot");
		herotexture = atlas.findRegion("darkhero1");
		enemytexture = atlas.findRegion("greyadept2");
		enemytexture.flip(true, false);
		enemytexture = atlas.findRegion("greyadept1");
		enemytexture.flip(true, false);
		manabartexture = atlas.findRegion("bluemana");
		buff1texture = atlas.findRegion("uppower");
		if (game.stage % 2 == 1 & game.stage < 4){
			buff2texture = atlas.findRegion("upspell");
		}
		else {
			buff2texture = atlas.findRegion("upcoin");
		}

		buff3texture = atlas.findRegion("upmagskill");
		texturesarr = new AtlasRegion[]{pustotatexture = atlas.findRegion("pustota"), spell1texture = atlas.findRegion(spell1.gettexture()),spell2texture = atlas.findRegion(spell2.gettexture()), spell3texture = atlas.findRegion(spell3.gettexture()), spell4texture = atlas.findRegion(spell4.gettexture()), spell5texture = atlas.findRegion(spell5.gettexture()), spell6texture = atlas.findRegion(spell6.gettexture())};
		//enemytexturesarr = new AtlasRegion[]{pustotatexture = atlas.findRegion("pustota"), enemyspell1texture = atlas.findRegion(enemyspell1.gettexture()),enemyspell2texture = atlas.findRegion(enemyspell2.gettexture()), enemyspell3texture = atlas.findRegion(enemyspell3.gettexture()), enemyspell4texture = atlas.findRegion(enemyspell4.gettexture()), enemyspell5texture = atlas.findRegion(enemyspell5.gettexture()), enemyspell6texture = atlas.findRegion(enemyspell6.gettexture())};
		enemytexturesarr = new AtlasRegion[]{pustotatexture = atlas.findRegion("pustota"), enemyspell1texture = atlas.findRegion("pustota"),enemyspell2texture = atlas.findRegion("pustota"), enemyspell3texture = atlas.findRegion("pustota"), enemyspell4texture = atlas.findRegion("pustota"), enemyspell5texture = atlas.findRegion("pustota"), enemyspell6texture = atlas.findRegion("pustota")};



		healthfont = new BitmapFont();
		healthfont.setColor(Color.RED);
		manafont = new BitmapFont();
		manafont.setColor(Color.BLUE);
		modattackfont = new BitmapFont();
		modattackfont.setColor(Color.CORAL);
		moddeffont = new BitmapFont();
		moddeffont.setColor(Color.OLIVE);
		modcontrolfont = new BitmapFont();
		modcontrolfont.setColor(Color.PINK);
		damageview = new BitmapFont();
		damageview.setColor(Color.RED);
		enemydamageview = new BitmapFont();
		enemydamageview.setColor(Color.RED);
		congratulations = new BitmapFont();
		congratulations.setColor(Color.RED);



		//-------------------------------------------------------------------------------------------------РЕКТЫ----------------------------------------------------------------------------------------------------------------------------------
		fon = new Rectangle();
		fon.x = 0;
		fon.y = 0;
		fon.width = 800;
		fon.height = 480;

		heroparams = new Rectangle();
		heroparams.width = 200;
		heroparams.height = 300;
		heroparams.x = 50;
		heroparams.y = fon.height - heroparams.height;


		slot1 = new Rectangle();
		slot1.x = 150;
		slot1.y = 10;
		slot1.width = 50;
		slot1.height = 50;

		slot2 = new Rectangle();
		slot2.x = slot1.x  + slot1.width + 50;
		slot2.y = slot1.y;
		slot2.width = slot1.width;
		slot2.height = slot1.height;

		slot3 = new Rectangle();
		slot3.x = slot1.x  + 2*(slot1.width + 50);
		slot3.y = slot1.y;
		slot3.width = slot1.width;
		slot3.height = slot1.height;

		slot4 = new Rectangle();
		slot4.x = slot1.x  + 3*(slot1.width + 50);
		slot4.y = slot1.y;
		slot4.width = slot1.width;
		slot4.height = slot1.height;

		slot5 = new Rectangle();
		slot5.x = slot1.x  + 4*(slot1.width + 50);
		slot5.y = slot1.y;
		slot5.width = slot1.width;
		slot5.height = slot1.height;

		slot6 = new Rectangle();
		slot6.x = slot1.x  + 5*(slot1.width + 50);
		slot6.y = slot1.y;
		slot6.width = slot1.width;
		slot6.height = slot1.height;

		hero = new Rectangle();
		hero.x = 100;
		hero.y = 60;
		hero.width = 100;
		hero.height = 100;

		enemy = new Rectangle();
		enemy.x = 600;
		enemy.y = 60;
		enemy.width = 100;
		enemy.height = 100;

		spell1rect = new Rectangle();
		spell1rect.x = slot1.x + 5;
		spell1rect.y = slot1.y + 5;
		spell1rect.width = slot1.width - 10;
		spell1rect.height = slot1.height - 10;

		spell2rect = new Rectangle();
		spell2rect.x = slot2.x + 5;
		spell2rect.y = slot2.y + 5;
		spell2rect.width = slot2.width - 10;
		spell2rect.height = slot2.height - 10;

		spell3rect = new Rectangle();
		spell3rect.x = slot3.x + 5;
		spell3rect.y = slot3.y + 5;
		spell3rect.width = slot3.width - 10;
		spell3rect.height = slot3.height - 10;

		spell4rect = new Rectangle();
		spell4rect.x = slot4.x + 5;
		spell4rect.y = slot4.y + 5;
		spell4rect.width = slot4.width - 10;
		spell4rect.height = slot4.height - 10;

		spell5rect = new Rectangle();
		spell5rect.x = slot5.x + 5;
		spell5rect.y = slot5.y + 5;
		spell5rect.width = slot5.width - 10;
		spell5rect.height = slot5.height - 10;

		spell6rect = new Rectangle();
		spell6rect.x = slot6.x + 5;
		spell6rect.y = slot6.y + 5;
		spell6rect.width = slot6.width - 10;
		spell6rect.height = slot6.height - 10;

		enemyspell1rect = new Rectangle();
		enemyspell1rect.x = slot1.x + 5;
		enemyspell1rect.y = slot1.y + 5;
		enemyspell1rect.width = slot1.width - 10;
		enemyspell1rect.height = slot1.height - 10;

		enemyspell2rect = new Rectangle();
		enemyspell2rect.x = slot1.x + 5;
		enemyspell2rect.y = slot1.y + 5;
		enemyspell2rect.width = slot1.width - 10;
		enemyspell2rect.height = slot1.height - 10;

		enemyspell3rect = new Rectangle();
		enemyspell3rect.x = slot1.x + 5;
		enemyspell3rect.y = slot1.y + 5;
		enemyspell3rect.width = slot1.width - 10;
		enemyspell3rect.height = slot1.height - 10;

		enemyspell4rect = new Rectangle();
		enemyspell4rect.x = slot1.x + 5;
		enemyspell4rect.y = slot1.y + 5;
		enemyspell4rect.width = slot1.width - 10;
		enemyspell4rect.height = slot1.height - 10;

		enemyspell5rect = new Rectangle();
		enemyspell5rect.x = slot1.x + 5;
		enemyspell5rect.y = slot1.y + 5;
		enemyspell5rect.width = slot1.width - 10;
		enemyspell5rect.height = slot1.height - 10;

		enemyspell6rect = new Rectangle();
		enemyspell6rect.x = slot1.x + 5;
		enemyspell6rect.y = slot1.y + 5;
		enemyspell6rect.width = slot1.width - 10;
		enemyspell6rect.height = slot1.height - 10;

		manabar = new Rectangle();
		manabar.x = 0;
		manabar.y = 0;
		manabar.width = mana;
		manabar.height = 50;

		buff1 = new Rectangle();
		buff1.x = 100;
		buff1.y = 100;
		buff1.width = 100;
		buff1.height = 100;

		buff2 = new Rectangle();
		buff2.x = buff1.x + 200;
		buff2.y = buff1.y;
		buff2.width = buff1.width;
		buff2.height = buff1.height;

		buff3 = new Rectangle();
		buff3.x = buff2.x + 200;
		buff3.y = buff1.y;
		buff3.width = buff1.width;
		buff3.height = buff1.height;
		//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

		slotrects = new Rectangle[]{slot1, slot2, slot3, slot4, slot5, slot6};

		touchPos = new Vector3();



		new Thread(new Runnable() {
			@Override
			public void run() {
				int spcall = 6;
				for (String enemywhatspell : game.enemywhatspells) {
					if(Integer.parseInt(enemywhatspell) == 1){
						spcall -= 1;
					}
				}
				try {
					sleep(2000);
					while (true){
						sleep(godice(100, 1500));
						switch (godice(1, spcall)){
							case 1:
								if(enemymana >= enemyspell1.getmanacost() & !enemyspell1.indo){
									enemymana -=  enemyspell1.getmanacost();
									new Thread(new Runnable() {
										@Override
										public void run() {
											enemyshablon.gospell1();
										}
									}).start();

								}
								break;

							case 2:
								if(enemymana >= enemyspell2.getmanacost() & !enemyspell2.indo){
									enemymana -=  enemyspell2.getmanacost();
									new Thread(new Runnable() {
										@Override
										public void run() {
											enemyshablon.gospell2();
										}
									}).start();
								}
								break;

							case 3:
								if(enemymana >= enemyspell3.getmanacost() & !enemyspell3.indo){
									enemymana -=  enemyspell3.getmanacost();
									new Thread(new Runnable() {
										@Override
										public void run() {
											enemyshablon.gospell3();
										}
									}).start();
								}
								break;

							case 4:
								if(enemymana >= enemyspell4.getmanacost() & !enemyspell4.indo){
									enemymana -=  enemyspell4.getmanacost();
									new Thread(new Runnable() {
										@Override
										public void run() {
											enemyshablon.gospell4();
										}
									}).start();
								}
								break;

							case 5:
								if(enemymana >= enemyspell5.getmanacost() & !enemyspell5.indo){
									enemymana -=  enemyspell5.getmanacost();
									new Thread(new Runnable() {
										@Override
										public void run() {
											enemyshablon.gospell5();
										}
									}).start();
								}
								break;

							case 6:
								if(enemymana >= enemyspell6.getmanacost() & !enemyspell6.indo){
									enemymana -=  enemyspell6.getmanacost();
									new Thread(new Runnable() {
										@Override
										public void run() {
											enemyshablon.gospell6();
										}
									}).start();
								}
								break;


						}
					}
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		}).start();
		heroparamssprite.setProjectionMatrix(camera.combined);
		slot1sprite.setProjectionMatrix(camera.combined);
		slot2sprite.setProjectionMatrix(camera.combined);
		slot3sprite.setProjectionMatrix(camera.combined);
		slot4sprite.setProjectionMatrix(camera.combined);
		slot5sprite.setProjectionMatrix(camera.combined);
		slot6sprite.setProjectionMatrix(camera.combined);
		herosprite.setProjectionMatrix(camera.combined);
		enemysprite.setProjectionMatrix(camera.combined);
		spell1sprite.setProjectionMatrix(camera.combined);
		spell2sprite.setProjectionMatrix(camera.combined);
		spell3sprite.setProjectionMatrix(camera.combined);
		spell4sprite.setProjectionMatrix(camera.combined);
		spell5sprite.setProjectionMatrix(camera.combined);
		spell6sprite.setProjectionMatrix(camera.combined);
		enemyspell1sprite.setProjectionMatrix(camera.combined);
		enemyspell2sprite.setProjectionMatrix(camera.combined);
		enemyspell3sprite.setProjectionMatrix(camera.combined);
		enemyspell4sprite.setProjectionMatrix(camera.combined);
		enemyspell5sprite.setProjectionMatrix(camera.combined);
		enemyspell6sprite.setProjectionMatrix(camera.combined);
		manabarsprite.setProjectionMatrix(camera.combined);








	}

	@Override
	public void render (float delta) {

		camera.update();
		fonsprite.setProjectionMatrix(camera.combined);
		fonsprite.begin();
		fonsprite.draw(fontexture, fon.x, fon.y, fon.width,fon.height);
		fonsprite.end();
		if (pause == 0){




//			fonsprite.begin();
//			fonsprite.draw(fontexture, fon.x, fon.y, fon.width,fon.height);
//			damageview.draw(fonsprite, String.valueOf(health), hero.x, hero.y+hero.height);
//			enemydamageview.draw(fonsprite, String.valueOf(enemyhealth), enemy.x + enemy.width - 25, hero.y+hero.height);
//			fonsprite.end();

			herosprite.begin();
			herosprite.draw(herotexture, hero.x, hero.y, hero.width,hero.height);
			damageview.draw(herosprite, String.valueOf(health), hero.x, hero.y+hero.height);
			herosprite.end();

			enemysprite.begin();
			enemysprite.draw(enemytexture, enemy.x, enemy.y,  enemy.width/2,  enemy.height/2,  enemy.width,  enemy.height,  (enemy.width/2)/(enemy.height/2),  (enemy.height/2)/(enemy.width/2),  (enemy.width/2)/(enemy.height/2));
			enemydamageview.draw(enemysprite, String.valueOf(enemyhealth), enemy.x + enemy.width - 25, hero.y+hero.height);
			enemysprite.end();

			slot1sprite.begin();
			slot1sprite.draw(slottexture, slot1.x, slot1.y, slot1.width,slot1.height);
			slot1sprite.end();

			slot2sprite.begin();
			slot2sprite.draw(slottexture, slot2.x, slot2.y, slot2.width,slot2.height);
			slot2sprite.end();

			slot3sprite.begin();
			slot3sprite.draw(slottexture, slot3.x, slot3.y, slot3.width,slot3.height);
			slot3sprite.end();

			slot4sprite.begin();
			slot4sprite.draw(slottexture, slot4.x , slot4.y, slot4.width,slot4.height);
			slot4sprite.end();

			slot5sprite.begin();
			slot5sprite.draw(slottexture, slot5.x, slot5.y, slot5.width,slot5.height);
			slot5sprite.end();

			slot6sprite.begin();
			slot6sprite.draw(slottexture, slot6.x , slot6.y, slot6.width,slot6.height);
			slot6sprite.end();

			spell1sprite.begin();
			spell1sprite.draw(texturesarr[1], spell1rect.x, spell1rect.y, spell1rect.width,spell1rect.height);
			spell1sprite.end();

			spell2sprite.begin();
			spell2sprite.draw(texturesarr[2], spell2rect.x, spell2rect.y, spell2rect.width,spell2rect.height);
			spell2sprite.end();

			spell3sprite.begin();
			spell3sprite.draw(texturesarr[3], spell3rect.x, spell3rect.y, spell3rect.width,spell3rect.height);
			spell3sprite.end();

			spell4sprite.begin();
			spell4sprite.draw(texturesarr[4], spell4rect.x, spell4rect.y, spell4rect.width,spell4rect.height);
			spell4sprite.end();

			spell5sprite.begin();
			spell5sprite.draw(texturesarr[5], spell5rect.x, spell5rect.y, spell5rect.width,spell5rect.height);
			spell5sprite.end();

			spell6sprite.begin();
			spell6sprite.draw(texturesarr[6], spell6rect.x, spell6rect.y, spell6rect.width,spell6rect.height);
			spell6sprite.end();

			enemyspell1sprite.begin();
			enemytexturesarr[1].flip(true, false);
			enemyspell1sprite.draw(enemytexturesarr[1], enemyspell1rect.x, enemyspell1rect.y, enemyspell1rect.width, enemyspell1rect.height);
			enemytexturesarr[1].flip(true, false);
			enemyspell1sprite.end();

			enemyspell2sprite.begin();
			enemytexturesarr[2].flip(true, false);
			enemyspell2sprite.draw(enemytexturesarr[2], enemyspell2rect.x, enemyspell2rect.y, enemyspell2rect.width, enemyspell2rect.height);
			enemytexturesarr[2].flip(true, false);
			enemyspell2sprite.end();

			enemyspell3sprite.begin();
			enemytexturesarr[3].flip(true, false);
			enemyspell3sprite.draw(enemytexturesarr[3], enemyspell3rect.x, enemyspell3rect.y, enemyspell3rect.width, enemyspell3rect.height);
			enemytexturesarr[3].flip(true, false);
			enemyspell3sprite.end();

			enemyspell4sprite.begin();
			enemytexturesarr[4].flip(true, false);
			enemyspell4sprite.draw(enemytexturesarr[4], enemyspell4rect.x, enemyspell4rect.y, enemyspell4rect.width, enemyspell4rect.height);
			enemytexturesarr[4].flip(true, false);
			enemyspell4sprite.end();

			enemyspell5sprite.begin();
			enemytexturesarr[5].flip(true, false);
			enemyspell5sprite.draw(enemytexturesarr[5], enemyspell5rect.x, enemyspell5rect.y, enemyspell5rect.width, enemyspell5rect.height);
			enemytexturesarr[5].flip(true, false);
			enemyspell5sprite.end();

			enemyspell6sprite.begin();
			enemytexturesarr[6].flip(true, false);
			enemyspell6sprite.draw(enemytexturesarr[6], enemyspell6rect.x, enemyspell6rect.y, enemyspell6rect.width, enemyspell6rect.height);
			enemytexturesarr[6].flip(true, false);
			enemyspell6sprite.end();

			manabarsprite.begin();
			manabarsprite.draw(manabartexture, manabar.x, manabar.y, mana, manabar.height);
			manabarsprite.end();




			if(Gdx.input.isTouched()){
				touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
				camera.unproject(touchPos);
				if(hero.contains(touchPos.x, touchPos.y)){
					heroparamssprite.begin();
					heroparamssprite.draw(heroparamstexture, heroparams.x, heroparams.y, heroparams.width, heroparams.height);
					healthfont.draw(heroparamssprite,"Health "+String.valueOf(maxhealth), heroparams.x + 50, heroparams.y + heroparams.height - 50);
					manafont.draw(heroparamssprite,"Mana "+String.valueOf(maxmana), heroparams.x + 50, heroparams.y + heroparams.height - 80);
					modattackfont.draw(heroparamssprite,"Attack "+String.valueOf(modattack), heroparams.x + 50, heroparams.y + heroparams.height - 110);
					moddeffont.draw(heroparamssprite,"Deffenseve "+String.valueOf(moddef), heroparams.x + 50, heroparams.y + heroparams.height - 140);
					modcontrolfont.draw(heroparamssprite,"Control "+String.valueOf(modcontrol), heroparams.x + 50, heroparams.y + heroparams.height - 170);
					heroparamssprite.end();
					herosprite.begin();
					herosprite.setColor(0.3f, 0.3f, 0.3f, 0.8f);
					herosprite.end();

				}
				if (slot1.contains(touchPos.x, touchPos.y)){
					new Thread() {
						public void run() {

							if(!isspell1 & mana >= spell1.getmanacost()){
								isspell1 = true;
								mana -= spell1.getmanacost();
								if(!iscast){
									slot1.width += 2;
									slot1.height += 2;
									slot1.x -= 1;
									slot1.y -= 1;

									try {
										sleep(100);
									} catch (InterruptedException e) {
										throw new RuntimeException(e);
									}
									slot1.x += 1;
									slot1.y += 1;
									slot1.width -= 2;
									slot1.height -= 2;
									iscast = true;
									spell1.cast(1, spell1rect);

									iscast = false;
									spell1.go();

								}
								isspell1 = false;
							}



						}
					}.start();
				}
				if (slot2.contains(touchPos.x, touchPos.y)){
					new Thread() {
						public void run() {

							if(!isspell2 & mana >= spell2.getmanacost()){
								isspell2 = true;
								mana -= spell2.getmanacost();
								if(!iscast){
									slot2.width += 2;
									slot2.height += 2;
									slot2.x -= 1;
									slot2.y -= 1;
									try {
										sleep(100);
									} catch (InterruptedException e) {
										throw new RuntimeException(e);
									}
									slot2.x += 1;
									slot2.y += 1;
									slot2.width -= 2;
									slot2.height -= 2;
									iscast = true;
									spell2.cast(2, spell2rect);
									iscast = false;
									spell2.go();

								}
								isspell2 = false;
							}



						}
					}.start();
				}
				if (slot3.contains(touchPos.x, touchPos.y)){
					new Thread() {
						public void run() {

							if(!isspell3 & mana >= spell3.getmanacost()){
								isspell3 = true;
								mana -= spell3.getmanacost();
								if(!iscast){
									slot3.width += 2;
									slot3.height += 2;
									slot3.x -= 1;
									slot3.y -= 1;
									try {
										sleep(100);
									} catch (InterruptedException e) {
										throw new RuntimeException(e);
									}
									slot3.x += 1;
									slot3.y += 1;
									slot3.width -= 2;
									slot3.height -= 2;
									iscast = true;
									spell3.cast(3, spell3rect);
									iscast = false;
									spell3.go();

								}
								isspell3 = false;
							}



						}
					}.start();
				}
				if (slot4.contains(touchPos.x, touchPos.y)){
					new Thread() {
						public void run() {

							if(!isspell4 & mana >= spell4.getmanacost()){
								isspell4 = true;
								mana -= spell4.getmanacost();
								if(!iscast){
									slot4.width += 2;
									slot4.height += 2;
									slot4.x -= 1;
									slot4.y -= 1;
									try {
										sleep(100);
									} catch (InterruptedException e) {
										throw new RuntimeException(e);
									}
									slot4.x += 1;
									slot4.y += 1;
									slot4.width -= 2;
									slot4.height -= 2;
									iscast = true;
									spell4.cast(4, spell4rect);
									iscast = false;
									spell4.go();

								}
								isspell4 = false;
							}



						}
					}.start();
				}
				if (slot5.contains(touchPos.x, touchPos.y)){
					new Thread() {
						public void run() {

							if(!isspell5 & mana >= spell5.getmanacost()){
								isspell5 = true;
								mana -= spell5.getmanacost();
								if(!iscast){
									slot5.width += 2;
									slot5.height += 2;
									slot5.x -= 1;
									slot5.y -= 1;
									try {
										sleep(100);
									} catch (InterruptedException e) {
										throw new RuntimeException(e);
									}
									slot5.x += 1;
									slot5.y += 1;
									slot5.width -= 2;
									slot5.height -= 2;
									iscast = true;
									spell5.cast(5, spell5rect);
									iscast = false;
									spell5.go();

								}
								isspell5 = false;
							}



						}
					}.start();
				}
				if (slot6.contains(touchPos.x, touchPos.y)){
					new Thread() {
						public void run() {

							if(!isspell6 & mana >= spell6.getmanacost()){
								isspell6 = true;
								mana -= spell6.getmanacost();
								if(!iscast){
									slot6.width += 2;
									slot6.height += 2;
									slot6.x -= 1;
									slot6.y -= 1;
									try {
										sleep(100);
									} catch (InterruptedException e) {
										throw new RuntimeException(e);
									}
									slot6.x += 1;
									slot6.y += 1;
									slot6.width -= 2;
									slot6.height -= 2;
									iscast = true;
									spell6.cast(6, spell6rect);
									iscast = false;
									spell6.go();

								}
								isspell6 = false;
							}



						}
					}.start();
				}

			}
			else {
				herosprite.setColor(1, 1, 1, 1);
			}
			if ((TimeUtils.nanoTime() - time) > 200000000){
				tic();

			}
			if (health <= 0){
				endFail();
			}
			if (enemyhealth <= 0){
				endWin();
			}
		}
		else if (pause == 2) {
			buff1sprite.setProjectionMatrix(camera.combined);
			buff2sprite.setProjectionMatrix(camera.combined);
			buff3sprite.setProjectionMatrix(camera.combined);

			buff1sprite.begin();
			buff1sprite.draw(buff1texture, buff1.x, buff1.y, buff1.width,buff1.height);
			congratulations.draw(buff1sprite, "CHOOSE BUFF", width/2, height/2);
			buff1sprite.end();

			buff2sprite.begin();
			buff2sprite.draw(buff2texture, buff2.x, buff2.y, buff2.width,buff2.height);
			buff2sprite.end();

			buff3sprite.begin();
			buff3sprite.draw(buff3texture, buff3.x, buff3.y, buff3.width,buff3.height);
			buff3sprite.end();

			if(Gdx.input.isTouched()) {
				touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
				camera.unproject(touchPos);
				if (buff1.contains(touchPos.x, touchPos.y)) {
					game.maxhealth += 5;
					game.maxmana += 5;
					game.setScreen(new MainMenuScreen(game, 5));
				}
				else if (buff2.contains(touchPos.x, touchPos.y)) {
					if (game.stage % 2 == 1 & game.stage < 4){
						game.whatspells[game.stage + 1] = String.valueOf(3 + game.stage);
					}
					else {
						game.coin += 1;
					}
					game.setScreen(new MainMenuScreen(game, 5));
					//Gdx.app.exit();
				}
				else if (buff3.contains(touchPos.x, touchPos.y)) {
					game.modattack += 1;
					game.moddef += 1;
					game.modcontrol += 1;
					game.setScreen(new MainMenuScreen(game, 5));
				}
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
	public void dispose () {
		fonsprite.dispose();
		healthfont.dispose();
		heroparamssprite.dispose();
		herosprite.dispose();
		enemysprite.dispose();
		spell1sprite.dispose();
		spell2sprite.dispose();
		spell3sprite.dispose();
		spell4sprite.dispose();
		spell5sprite.dispose();
		spell6sprite.dispose();
		enemyspell1sprite.dispose();
		enemyspell2sprite.dispose();
		enemyspell3sprite.dispose();
		enemyspell4sprite.dispose();
		enemyspell5sprite.dispose();
		enemyspell6sprite.dispose();
		slot1sprite.dispose();
		slot2sprite.dispose();
		slot3sprite.dispose();
		slot4sprite.dispose();
		slot5sprite.dispose();
		slot6sprite.dispose();
	}

	class EnemyShablon{
		public void gospell1(){
			try {
				sleep(100);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			enemyspell1.cast(1, enemyspell1rect);
			enemyspell1.go();
		}
		public void gospell2(){
			try {
				sleep(100);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			enemyspell2.cast(2, enemyspell2rect);
			enemyspell2.go();
		}
		public void gospell3(){
			try {
				sleep(100);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			enemyspell3.cast(3, enemyspell3rect);
			enemyspell3.go();
		}
		public void gospell4(){
			try {
				sleep(100);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			enemyspell4.cast(4, enemyspell4rect);
			enemyspell4.go();
		}
		public void gospell5(){
			try {
				sleep(100);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			enemyspell5.cast(5, enemyspell5rect);
			enemyspell5.go();
		}
		public void gospell6(){
			try {
				sleep(100);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			enemyspell6.cast(6, enemyspell6rect);
			enemyspell6.go();
		}


	}

	class Spell{
		int spellid;
		Rectangle spellrect;
		boolean indo = false;
		public int getmanacost(){return 0;}
		public String gettexture(){
			return "pustota";
		}
		public boolean isindo(){
			return indo;
		}
		public void  cast(int textureid, Rectangle spellrectin){
			spellid =textureid;
			spellrect = spellrectin;
		}
		public void go(){}
		public void gotoslot() {
			try {
				sleep(1);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			indo = false;
			Gdx.app.postRunnable(new Runnable() {
				@Override
				public void run() {

					texturesarr[spellid] =atlas.findRegion(spellsforslots[spellid-1].gettexture());
					spellrect.x = slotrects[spellid-1].x + 5;
					spellrect.y = slotrects[spellid-1].y + 5;
					spellrect.width = slotrects[spellid-1].width - 10;
					spellrect.height = slotrects[spellid-1].height - 10;
				}
			});
		}
	}

	class DarkBall extends  Spell{
		@Override
		public String gettexture(){
			return "darkball1";
		}
		@Override
		public int getmanacost(){return 5;}


		@Override
		public void  cast(int textureid, Rectangle spellrectin){
			try {
				indo = true;
				spellid =textureid;
				spellrect = spellrectin;
				Gdx.app.postRunnable(new Runnable() {
					@Override
					public void run() {
						texturesarr[spellid] = atlas.findRegion("darkball1");
						spellrect.x  = hero.x + 50;
						spellrect.y  = hero.y + 20;
						spellrect.width  = 80;
						spellrect.height  = 80;
					}
				});

				sleep(100);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		@Override
		public void go() {
			try{
				while (spellrect.x < enemy.x-50){
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {
							texturesarr[spellid] = atlas.findRegion("darkball1");
							spellrect.x += 5;
						}
					});
					sleep(25);
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {
							texturesarr[spellid] = atlas.findRegion("darkball2");
							spellrect.x += 5;
						}
					});
					sleep(25);
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {
							texturesarr[spellid] = atlas.findRegion("darkball3");
							spellrect.x += 5;
						}
					});
					sleep(25);
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {
							texturesarr[spellid] = atlas.findRegion("darkball4");
							spellrect.x += 5;
						}
					});
					sleep(25);

				}
				if ((godice(1, 20) + modcontrol) > (5 + enemymoddef)) {
					enemyhealth -= godice(1, 8) + modattack;
				}
				gotoslot();
			}catch (Exception e) {
				throw new RuntimeException(e);
			}

		}
	}
	class EnemyDarkBall extends  Spell{
		@Override
		public String gettexture(){
			return "darkball1";
		}
		@Override
		public int getmanacost(){return 5;}


		@Override
		public void  cast(int textureid, Rectangle spellrectin){
			try {
				indo = true;
				spellid =textureid;
				spellrect = spellrectin;
				Gdx.app.postRunnable(new Runnable() {
					@Override
					public void run() {
						enemytexturesarr[spellid] = atlas.findRegion("darkball1");
						spellrect.x  = enemy.x - 50;
						spellrect.y  = enemy.y + 20;
						spellrect.width  = 80;
						spellrect.height  = 80;
					}
				});

				sleep(100);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		@Override
		public void go() {
			try{
				while (spellrect.x > hero.x+75){
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {
							enemytexturesarr[spellid] = atlas.findRegion("darkball1");
							spellrect.x -= 5;
						}
					});
					sleep(25);
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {
							enemytexturesarr[spellid] = atlas.findRegion("darkball2");
							spellrect.x -= 5;
						}
					});
					sleep(25);
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {
							enemytexturesarr[spellid] = atlas.findRegion("darkball3");
							spellrect.x -= 5;
						}
					});
					sleep(25);
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {
							enemytexturesarr[spellid] = atlas.findRegion("darkball4");
							spellrect.x -= 5;
						}
					});
					sleep(25);

				}
				if ((godice(1, 20) + enemymodcontrol) > (5 + moddef)) {
					health -= godice(1, 8) + enemymodattack;
				}
				Gdx.app.postRunnable(new Runnable() {
					@Override
					public void run() {
						enemytexturesarr[spellid] = atlas.findRegion("pustota");

					}
				});
				indo = false;
			}catch (Exception e) {
				throw new RuntimeException(e);
			}

		}
	}

	class DarkShield extends  Spell{
		@Override
		public int getmanacost(){return 5;}
		@Override
		public String gettexture(){
			return "darkshield1";
		}
		@Override
		public void  cast(int textureid, Rectangle spellrectin){
			try {
				indo = true;
				spellid =textureid;
				spellrect = spellrectin;
				Gdx.app.postRunnable(new Runnable() {
					@Override
					public void run() {
						texturesarr[spellid] = atlas.findRegion("darkshield1");
						spellrect.x  = hero.x + 50;
						spellrect.y  = hero.y + 0;
						spellrect.width  = 100;
						spellrect.height  = 100;

					}
				});

				sleep(100);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		@Override
		public void go() {
			try{
				moddef += 10;
				for(int i = 0; i < 30; i++){
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {
							texturesarr[spellid] = atlas.findRegion("darkshield1");
						}
					});
					sleep(50);
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {
							texturesarr[spellid] = atlas.findRegion("darkshield2");
						}
					});
					sleep(50);


				}
				moddef -= 10;
				gotoslot();

			}catch (Exception e) {
				throw new RuntimeException(e);
			}

		}
	}
	class EnemyDarkShield extends  Spell{
		@Override
		public int getmanacost(){return 5;}
		@Override
		public String gettexture(){
			return "darkshield1";
		}
		@Override
		public void  cast(int textureid, Rectangle spellrectin){
			try {
				indo = true;
				spellid =textureid;
				spellrect = spellrectin;
				Gdx.app.postRunnable(new Runnable() {
					@Override
					public void run() {
						enemytexturesarr[spellid] = atlas.findRegion("darkshield1");
						spellrect.x  = enemy.x - 50;
						spellrect.y  = enemy.y + 0;
						spellrect.width  = 100;
						spellrect.height  = 100;

					}
				});

				sleep(100);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		@Override
		public void go() {
			try{
				enemymoddef += 10;
				for(int i = 0; i < 30; i++){
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {enemytexturesarr[spellid] = atlas.findRegion("darkshield1");}
					});
					sleep(50);
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {
							enemytexturesarr[spellid] = atlas.findRegion("darkshield2");
						}
					});
					sleep(50);


				}
				enemymoddef -= 10;
				Gdx.app.postRunnable(new Runnable() {
					@Override
					public void run() {
						enemytexturesarr[spellid] = atlas.findRegion("pustota");

					}
				});
				indo = false;

			}catch (Exception e) {
				throw new RuntimeException(e);
			}

		}
	}

	class DarkCurse extends  Spell{
		@Override
		public int getmanacost(){return 10;}
		@Override
		public String gettexture(){
			return "darkcurse1";
		}
		@Override
		public void  cast(int textureid, Rectangle spellrectin){
			try {
				indo = true;
				spellid =textureid;
				spellrect = spellrectin;
				Gdx.app.postRunnable(new Runnable() {
					@Override
					public void run() {
						texturesarr[spellid] = atlas.findRegion("darkcurse1");
						spellrect.x  = enemy.x;
						spellrect.y = enemy.y;
						spellrect.width = enemy.width;
						spellrect.height = enemy.height;

					}
				});

				sleep(100);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		@Override
		public void go() {
			try{
				enemymoddef -= 5;
				enemymodcontrol -= 5;
				for(int i = 0; i < 30; i++){
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {
							texturesarr[spellid] = atlas.findRegion("darkcurse1");
						}
					});
					sleep(25);
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {
							texturesarr[spellid] = atlas.findRegion("darkcurse2");
						}
					});
					sleep(25);
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {
							texturesarr[spellid] = atlas.findRegion("darkcurse3");
						}
					});
					sleep(25);

				}
				enemymoddef += 5;
				enemymodcontrol += 5;
				gotoslot();

			}catch (Exception e) {
				throw new RuntimeException(e);
			}

		}
	}
	class EnemyDarkCurse extends  Spell{
		@Override
		public int getmanacost(){return 10;}
		@Override
		public String gettexture(){
			return "darkcurse1";
		}
		@Override
		public void  cast(int textureid, Rectangle spellrectin){
			try {
				indo = true;
				spellid =textureid;
				spellrect = spellrectin;
				Gdx.app.postRunnable(new Runnable() {
					@Override
					public void run() {
						enemytexturesarr[spellid] = atlas.findRegion("darkcurse1");
						spellrect.x  = hero.x;
						spellrect.y = hero.y;
						spellrect.width = hero.width;
						spellrect.height = hero.height;

					}
				});

				sleep(100);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		@Override
		public void go() {
			try{
				moddef -= 5;
				modcontrol -= 5;
				for(int i = 0; i < 30; i++){
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {
							enemytexturesarr[spellid] = atlas.findRegion("darkcurse1");
						}
					});
					sleep(25);
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {
							enemytexturesarr[spellid] = atlas.findRegion("darkcurse2");
						}
					});
					sleep(25);
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {
							enemytexturesarr[spellid] = atlas.findRegion("darkcurse3");
						}
					});
					sleep(25);

				}
				moddef += 5;
				modcontrol += 5;
				Gdx.app.postRunnable(new Runnable() {
					@Override
					public void run() {
						enemytexturesarr[spellid] = atlas.findRegion("pustota");
					}
				});

			}catch (Exception e) {
				throw new RuntimeException(e);
			}
			indo = false;

		}
	}

	class DarkTriangle extends  Spell{
		@Override
		public String gettexture(){
			return "triangle";
		}
		@Override
		public int getmanacost(){return 25;}


		@Override
		public void  cast(int textureid, Rectangle spellrectin){
			try {
				indo = true;
				spellid =textureid;
				spellrect = spellrectin;
				Gdx.app.postRunnable(new Runnable() {
					@Override
					public void run() {
						texturesarr[spellid] = atlas.findRegion("triangle");
						spellrect.x  = hero.x + 50;
						spellrect.y  = hero.y + 200;
						spellrect.width  = 80;
						spellrect.height  = 80;
					}
				});

				sleep(100);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		@Override
		public void go() {
			try{
				while (spellrect.x < enemy.x-50){
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {
							texturesarr[spellid] = atlas.findRegion("triangle");
							spellrect.x += 10;
							spellrect.y -= 4;
						}
					});
					sleep(25);
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {
							texturesarr[spellid] = atlas.findRegion("triangle1");
							spellrect.x += 10;
							spellrect.y -= 4;
						}
					});
					sleep(25);
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {
							texturesarr[spellid] = atlas.findRegion("triangle2");
							spellrect.x += 10;
							spellrect.y -= 4;
						}
					});
					sleep(25);

				}
				if ((godice(1, 20) + modcontrol) > (5 + enemymoddef)) {
					enemyhealth -= godice(1, 12) + modattack;
				}
				gotoslot();
			}catch (Exception e) {
				throw new RuntimeException(e);
			}

		}
	}
	class EnemyDarkTriangle extends  Spell{
		@Override
		public String gettexture(){
			return "triangle";
		}
		@Override
		public int getmanacost(){return 25;}


		@Override
		public void  cast(int textureid, Rectangle spellrectin){
			try {
				indo = true;
				spellid =textureid;
				spellrect = spellrectin;
				Gdx.app.postRunnable(new Runnable() {
					@Override
					public void run() {
						enemytexturesarr[spellid] = atlas.findRegion("triangle");
						spellrect.x  = enemy.x - 50;
						spellrect.y  = enemy.y + 200;
						spellrect.width  = 80;
						spellrect.height  = 80;
					}
				});

				sleep(100);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		@Override
		public void go() {
			try{
				while (spellrect.x > hero.x+50){
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {
							enemytexturesarr[spellid] = atlas.findRegion("triangle");
							spellrect.x -= 10;
							spellrect.y -= 4;
						}
					});
					sleep(25);
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {
							enemytexturesarr[spellid] = atlas.findRegion("triangle1");
							spellrect.x -= 10;
							spellrect.y -= 4;
						}
					});
					sleep(25);
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {
							enemytexturesarr[spellid] = atlas.findRegion("triangle2");
							spellrect.x -= 10;
							spellrect.y -= 4;
						}
					});
					sleep(25);

				}
				if ((godice(1, 20) + enemymodcontrol) > (5 + moddef)) {
					health -= godice(1, 12) + enemymodattack;
				}
				Gdx.app.postRunnable(new Runnable() {
					@Override
					public void run() {
						enemytexturesarr[spellid] = atlas.findRegion("pustota");
					}
				});
				indo = false;
			}catch (Exception e) {
				throw new RuntimeException(e);
			}

		}
	}

	class DarkLine extends Spell{
		@Override
		public int getmanacost(){return 15;}
		@Override
		public String gettexture(){
			return "darkline1";
		}
		@Override
		public void  cast(int textureid, Rectangle spellrectin){
			try {
				indo = true;
				spellid =textureid;
				spellrect = spellrectin;
				Gdx.app.postRunnable(new Runnable() {
					@Override
					public void run() {
						texturesarr[spellid] = atlas.findRegion("darkline1");
						spellrect.x  = hero.x + hero.width;
						spellrect.y  = hero.y + 30;
						spellrect.width  = 420;
						spellrect.height  = 10;

					}
				});

				sleep(100);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		@Override
		public void go() {
			try{
				for(int i = 0; i < 30; i++){
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {
							texturesarr[spellid] = atlas.findRegion("darkline1");
						}
					});
					sleep(35);
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {
							texturesarr[spellid] = atlas.findRegion("darkline2");
						}
					});
					sleep(35);
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {
							texturesarr[spellid] = atlas.findRegion("darkline3");
						}
					});
					sleep(35);
				}
				if ((godice(1, 20) + modcontrol) > (5 + enemymoddef)) {
					enemyhealth -= godice(1, 14) + modattack;
				}
				gotoslot();

			}catch (Exception e) {
				throw new RuntimeException(e);
			}

		}
	}
	class EnemyDarkLine extends Spell{
		@Override
		public int getmanacost(){return 15;}
		@Override
		public String gettexture(){
			return "darkline1";
		}
		@Override
		public void  cast(int textureid, Rectangle spellrectin){
			try {
				indo = true;
				spellid =textureid;
				spellrect = spellrectin;
				Gdx.app.postRunnable(new Runnable() {
					@Override
					public void run() {
						enemytexturesarr[spellid] = atlas.findRegion("darkline1");
						spellrect.x  = hero.x + 80;
						spellrect.y  = hero.y + 30;
						spellrect.width  = 420;
						spellrect.height  = 10;

					}
				});

				sleep(100);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		@Override
		public void go() {
			try{
				for(int i = 0; i < 30; i++){
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {
							enemytexturesarr[spellid] = atlas.findRegion("darkline1");
						}
					});
					sleep(35);
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {
							enemytexturesarr[spellid] = atlas.findRegion("darkline2");
						}
					});
					sleep(35);
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {
							enemytexturesarr[spellid] = atlas.findRegion("darkline3");
						}
					});
					sleep(35);
				}
				if ((godice(1, 20) + enemymodcontrol) > (5 + moddef)) {
					health -= godice(1, 14) + enemymodattack;
				}
				Gdx.app.postRunnable(new Runnable() {
					@Override
					public void run() {
						enemytexturesarr[spellid] = atlas.findRegion("pustota");
					}
				});
				indo = false;

			}catch (Exception e) {
				throw new RuntimeException(e);
			}

		}
	}

	class DarkShadow extends  Spell{
		@Override
		public String gettexture(){
			return "darkshadow1";
		}
		@Override
		public int getmanacost(){return 20;}


		@Override
		public void  cast(int textureid, Rectangle spellrectin){
			try {
				indo = true;
				spellid =textureid;
				spellrect = spellrectin;
				Gdx.app.postRunnable(new Runnable() {
					@Override
					public void run() {
						texturesarr[spellid] = atlas.findRegion("darkshadow1");
						spellrect.x  = hero.x + 50;
						spellrect.y  = hero.y + 20;
						spellrect.width  = 80;
						spellrect.height  = 80;
					}
				});

				sleep(100);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		@Override
		public void go() {
			try{
				while (spellrect.x < enemy.x-50){
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {
							texturesarr[spellid] = atlas.findRegion("darkshadow1");
							spellrect.x += 5;
						}
					});
					sleep(25);
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {
							texturesarr[spellid] = atlas.findRegion("darkshadow2");
							spellrect.x += 5;
						}
					});
					sleep(25);

				}
				if ((godice(1, 20) + modcontrol) > (5 + enemymoddef)) {
					enemyhealth -= godice(1, 16) + modattack;
				}
				Gdx.app.postRunnable(new Runnable() {
					@Override
					public void run() {
						texturesarr[spellid] = atlas.findRegion("pustota");
					}
				});
				enemymodattack-=5;
				enemymoddef-=5;
				enemymodcontrol-=5;
				sleep(4000);
				enemymodattack+=5;
				enemymoddef+=5;
				enemymodcontrol+=5;
				gotoslot();

			}catch (Exception e) {
				throw new RuntimeException(e);
			}

		}
	}
	class EnemyDarkShadow extends  Spell{
		@Override
		public String gettexture(){
			return "darkshadow1";
		}
		@Override
		public int getmanacost(){return 20;}


		@Override
		public void  cast(int textureid, Rectangle spellrectin){
			try {
				indo = true;
				spellid =textureid;
				spellrect = spellrectin;
				Gdx.app.postRunnable(new Runnable() {
					@Override
					public void run() {
						enemytexturesarr[spellid] = atlas.findRegion("darkshadow1");
						spellrect.x  = enemy.x - 50;
						spellrect.y  = enemy.y + 20;
						spellrect.width  = 80;
						spellrect.height  = 80;
					}
				});

				sleep(100);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		@Override
		public void go() {
			try{
				while (spellrect.x > hero.x){
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {
							enemytexturesarr[spellid] = atlas.findRegion("darkshadow1");
							spellrect.x -= 5;
						}
					});
					sleep(25);
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {
							enemytexturesarr[spellid] = atlas.findRegion("darkshadow2");
							spellrect.x -= 5;
						}
					});
					sleep(25);

				}
				if ((godice(1, 20) + enemymodcontrol) > (5 + moddef)) {
					health -= godice(1, 16) + enemymodattack;
				}
				Gdx.app.postRunnable(new Runnable() {
					@Override
					public void run() {
						enemytexturesarr[spellid] = atlas.findRegion("pustota");
					}
				});
				enemymodattack-=5;
				enemymoddef-=5;
				enemymodcontrol-=5;
				sleep(4000);
				enemymodattack+=5;
				enemymoddef+=5;
				enemymodcontrol+=5;
				indo = false;

			}catch (Exception e) {
				throw new RuntimeException(e);
			}

		}
	}

	class LightBall extends  Spell{
		@Override
		public String gettexture(){
			return "lightball1";
		}
		@Override
		public int getmanacost(){return 5;}


		@Override
		public void  cast(int textureid, Rectangle spellrectin){
			try {
				indo = true;
				spellid =textureid;
				spellrect = spellrectin;
				Gdx.app.postRunnable(new Runnable() {
					@Override
					public void run() {
						texturesarr[spellid] = atlas.findRegion("lightball1");
						spellrect.x  = hero.x + 50;
						spellrect.y  = hero.y + 20;
						spellrect.width  = 80;
						spellrect.height  = 80;
					}
				});

				sleep(100);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		@Override
		public void go() {
			try{
				while (spellrect.x < enemy.x-50){
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {
							texturesarr[spellid] = atlas.findRegion("lightball1");
							spellrect.x += 5;
						}
					});
					sleep(25);
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {
							texturesarr[spellid] = atlas.findRegion("lightball2");
							spellrect.x += 5;
						}
					});
					sleep(25);
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {
							texturesarr[spellid] = atlas.findRegion("lightball3");
							spellrect.x += 5;
						}
					});
					sleep(25);
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {
							texturesarr[spellid] = atlas.findRegion("lightball4");
							spellrect.x += 5;
						}
					});
					sleep(25);

				}
				if ((godice(1, 20) + modcontrol) > (5 + enemymoddef)) {
					enemyhealth -= godice(1, 6) + modattack;
				}
				gotoslot();
			}catch (Exception e) {
				throw new RuntimeException(e);
			}

		}
	}
	class EnemyLightBall extends  Spell{
		@Override
		public String gettexture(){
			return "pustota";
		}
		@Override
		public int getmanacost(){return 7;}


		@Override
		public void  cast(int textureid, Rectangle spellrectin){
			try {
				indo = true;
				spellid =textureid;
				spellrect = spellrectin;
				Gdx.app.postRunnable(new Runnable() {
					@Override
					public void run() {
						enemytexturesarr[spellid] = atlas.findRegion("lightball1");
						spellrect.x  = enemy.x - 50;
						spellrect.y  = enemy.y + 20;
						spellrect.width  = 80;
						spellrect.height  = 80;
					}
				});

				sleep(100);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		@Override
		public void go() {
			try{
				while (spellrect.x > hero.x+75){
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {
							enemytexturesarr[spellid] = atlas.findRegion("lightball1");
							spellrect.x -= 5;
						}
					});
					sleep(25);
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {
							enemytexturesarr[spellid] = atlas.findRegion("lightball2");
							spellrect.x -= 5;
						}
					});
					sleep(25);
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {
							enemytexturesarr[spellid] = atlas.findRegion("lightball3");
							spellrect.x -= 5;
						}
					});
					sleep(25);
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {
							enemytexturesarr[spellid] = atlas.findRegion("lightball4");
							spellrect.x -= 5;
						}
					});
					sleep(25);

				}
				if ((godice(1, 20) + enemymodcontrol) > (5 + moddef)) {
					health -= godice(1, 6) + enemymodattack;
				}
				Gdx.app.postRunnable(new Runnable() {
					@Override
					public void run() {
						enemytexturesarr[spellid] = atlas.findRegion("pustota");

					}
				});
				indo = false;

			}catch (Exception e) {
				throw new RuntimeException(e);
			}

		}
	}

	class LightShield extends  Spell{
		@Override
		public int getmanacost(){return 5;}
		@Override
		public String gettexture(){
			return "lightshield1";
		}
		@Override
		public void  cast(int textureid, Rectangle spellrectin){
			try {
				indo = true;
				spellid =textureid;
				spellrect = spellrectin;
				Gdx.app.postRunnable(new Runnable() {
					@Override
					public void run() {
						texturesarr[spellid] = atlas.findRegion("lightshield1");
						spellrect.x  = hero.x - hero.width/4;
						spellrect.y  = hero.y - hero.height/4;
						spellrect.width  = 150;
						spellrect.height  = 150;

					}
				});

				sleep(100);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		@Override
		public void go() {
			try{
				moddef += 12;
				for(int i = 0; i < 50; i++){
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {
							texturesarr[spellid] = atlas.findRegion("lightshield1");
						}
					});
					sleep(50);
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {
							texturesarr[spellid] = atlas.findRegion("lightshield2");
						}
					});
					sleep(50);


				}
				moddef -= 12;
				gotoslot();

			}catch (Exception e) {
				throw new RuntimeException(e);
			}

		}
	}
	class EnemylightShield extends  Spell{
		@Override
		public int getmanacost(){return 5;}
		@Override
		public String gettexture(){
			return "lightshield1";
		}
		@Override
		public void  cast(int textureid, Rectangle spellrectin){
			try {
				indo = true;
				spellid =textureid;
				spellrect = spellrectin;
				Gdx.app.postRunnable(new Runnable() {
					@Override
					public void run() {
						enemytexturesarr[spellid] = atlas.findRegion("lightshield1");
						spellrect.x  = enemy.x - enemy.width/4;
						spellrect.y  = enemy.y - enemy.height/4;
						spellrect.width  = 150;
						spellrect.height  = 150;

					}
				});

				sleep(100);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		@Override
		public void go() {
			try{
				enemymoddef += 10;
				for(int i = 0; i < 50; i++){
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {enemytexturesarr[spellid] = atlas.findRegion("lightshield1");}
					});
					sleep(50);
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {
							enemytexturesarr[spellid] = atlas.findRegion("lightshield2");
						}
					});
					sleep(50);


				}
				enemymoddef -= 10;
				Gdx.app.postRunnable(new Runnable() {
					@Override
					public void run() {
						enemytexturesarr[spellid] = atlas.findRegion("pustota");

					}
				});
				indo = false;

			}catch (Exception e) {
				throw new RuntimeException(e);
			}

		}
	}

	class LightHeal extends  Spell{
		@Override
		public int getmanacost(){return 30;}
		@Override
		public String gettexture(){
			return "lightheal1";
		}
		@Override
		public void  cast(int textureid, Rectangle spellrectin){
			try {
				indo = true;
				spellid =textureid;
				spellrect = spellrectin;
				Gdx.app.postRunnable(new Runnable() {
					@Override
					public void run() {
						texturesarr[spellid] = atlas.findRegion("lightheal1");
						spellrect.x  = hero.x;
						spellrect.y  = hero.y;
						spellrect.width  = hero.width;
						spellrect.height  = hero.height;

					}
				});

				sleep(100);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		@Override
		public void go() {
			try{
				for(int i = 0; i < 25; i++){
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {
							texturesarr[spellid] = atlas.findRegion("lightheal1");
						}
					});
					sleep(50);
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {
							texturesarr[spellid] = atlas.findRegion("lightheal2");
						}
					});
					sleep(50);
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {
							texturesarr[spellid] = atlas.findRegion("lightheal3");
						}
					});
					sleep(50);


				}
				health += 5;
				gotoslot();

			}catch (Exception e) {
				throw new RuntimeException(e);
			}

		}
	}
	class EnemylightHeal extends  Spell{
		@Override
		public int getmanacost(){return 30;}
		@Override
		public String gettexture(){
			return "lightheal1";
		}
		@Override
		public void  cast(int textureid, Rectangle spellrectin){
			try {
				indo = true;
				spellid =textureid;
				spellrect = spellrectin;
				Gdx.app.postRunnable(new Runnable() {
					@Override
					public void run() {
						enemytexturesarr[spellid] = atlas.findRegion("lightheal1");
						spellrect.x  = enemy.x;
						spellrect.y  = enemy.y;
						spellrect.width  = enemy.width;
						spellrect.height  = enemy.height;

					}
				});

				sleep(100);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		@Override
		public void go() {
			try{
				for(int i = 0; i < 25; i++){
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {enemytexturesarr[spellid] = atlas.findRegion("lightheal1");}
					});
					sleep(50);
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {
							enemytexturesarr[spellid] = atlas.findRegion("lightheal2");
						}
					});
					sleep(50);
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {
							enemytexturesarr[spellid] = atlas.findRegion("lightheal3");
						}
					});
					sleep(50);


				}
				enemyhealth+=5;
				Gdx.app.postRunnable(new Runnable() {
					@Override
					public void run() {
						enemytexturesarr[spellid] = atlas.findRegion("pustota");

					}
				});
				indo = false;

			}catch (Exception e) {
				throw new RuntimeException(e);
			}

		}
	}

	class LightPulling extends  Spell{
		@Override
		public int getmanacost(){return 15;}
		@Override
		public String gettexture(){
			return "lightpulling1";
		}
		@Override
		public void  cast(int textureid, Rectangle spellrectin){
			try {
				indo = true;
				spellid =textureid;
				spellrect = spellrectin;
				Gdx.app.postRunnable(new Runnable() {
					@Override
					public void run() {
						texturesarr[spellid] = atlas.findRegion("lightpulling1");
						spellrect.x  = hero.x + 100;
						spellrect.y = hero.y;
						spellrect.width = 100;
						spellrect.height = 100;

					}
				});

				sleep(100);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		@Override
		public void go() {
			try{

				for(int i = 0; i < 40; i++){
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {
							texturesarr[spellid] = atlas.findRegion("lightpulling1");
						}
					});
					sleep(25);
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {
							texturesarr[spellid] = atlas.findRegion("lightpulling2");
						}
					});
					sleep(25);

				}
				enemyhealth -= 10;
				health += 5;
				gotoslot();

			}catch (Exception e) {
				throw new RuntimeException(e);
			}

		}
	}
	class EnemyLightPulling extends  Spell{
		@Override
		public int getmanacost(){return 15;}
		@Override
		public String gettexture(){
			return "lightpulling1";
		}
		@Override
		public void  cast(int textureid, Rectangle spellrectin){
			try {
				indo = true;
				spellid =textureid;
				spellrect = spellrectin;
				Gdx.app.postRunnable(new Runnable() {
					@Override
					public void run() {
						enemytexturesarr[spellid] = atlas.findRegion("lightpulling1");
						spellrect.x  = enemy.x - 100;
						spellrect.y = enemy.y;
						spellrect.width = 100;
						spellrect.height = 100;

					}
				});

				sleep(100);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		@Override
		public void go() {
			try{
				moddef -= 5;
				modcontrol -= 5;
				for(int i = 0; i < 40; i++){
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {
							enemytexturesarr[spellid] = atlas.findRegion("lightpulling1");
						}
					});
					sleep(25);
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {
							enemytexturesarr[spellid] = atlas.findRegion("lightpulling2");
						}
					});
					sleep(25);

				}
				health -= 10;
				enemyhealth += 5;
				Gdx.app.postRunnable(new Runnable() {
					@Override
					public void run() {
						enemytexturesarr[spellid] = atlas.findRegion("pustota");
					}
				});

			}catch (Exception e) {
				throw new RuntimeException(e);
			}
			indo = false;

		}
	}

	class LightLine extends  Spell{
		@Override
		public int getmanacost(){return 12;}
		@Override
		public String gettexture(){
			return "lightline1";
		}
		@Override
		public void  cast(int textureid, Rectangle spellrectin){
			try {
				indo = true;
				spellid =textureid;
				spellrect = spellrectin;
				Gdx.app.postRunnable(new Runnable() {
					@Override
					public void run() {
						texturesarr[spellid] = atlas.findRegion("lightline1");
						spellrect.x  = enemy.x - 10;
						spellrect.y = enemy.y;
						spellrect.width = enemy.width + 20;
						spellrect.height = height;

					}
				});

				sleep(100);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		@Override
		public void go() {
			try{
				enemymodcontrol -= 5;
				for(int i = 0; i < 30; i++){
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {
							texturesarr[spellid] = atlas.findRegion("lightline1");
						}
					});
					sleep(25);
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {
							texturesarr[spellid] = atlas.findRegion("lightline2");
						}
					});
					sleep(25);

				}
				enemymodcontrol += 5;
				if ((godice(1, 20) + modcontrol) > (5 + enemymoddef)) {
					enemyhealth -= godice(1, 10) + modattack;
				}
				gotoslot();

			}catch (Exception e) {
				throw new RuntimeException(e);
			}

		}
	}
	class EnemyLightLine extends  Spell{
		@Override
		public int getmanacost(){return 12;}
		@Override
		public String gettexture(){
			return "lightline1";
		}
		@Override
		public void  cast(int textureid, Rectangle spellrectin){
			try {
				indo = true;
				spellid =textureid;
				spellrect = spellrectin;
				Gdx.app.postRunnable(new Runnable() {
					@Override
					public void run() {
						enemytexturesarr[spellid] = atlas.findRegion("lightline1");
						spellrect.x  = hero.x - 10;
						spellrect.y = hero.y;
						spellrect.width = hero.width + 20;
						spellrect.height = height;

					}
				});

				sleep(100);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		@Override
		public void go() {
			try{
				modcontrol -= 5;
				for(int i = 0; i < 30; i++){
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {
							enemytexturesarr[spellid] = atlas.findRegion("lightline1");
						}
					});
					sleep(25);
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {
							enemytexturesarr[spellid] = atlas.findRegion("lightline2");
						}
					});
					sleep(25);

				}
				modcontrol += 5;
				if ((godice(1, 20) + enemymodcontrol) > (5 + moddef)) {
					health -= godice(1, 10) + enemymodattack;
				}
				Gdx.app.postRunnable(new Runnable() {
					@Override
					public void run() {
						enemytexturesarr[spellid] = atlas.findRegion("pustota");
					}
				});

			}catch (Exception e) {
				throw new RuntimeException(e);
			}
			indo = false;

		}
	}

	class LightShadow extends  Spell{
		@Override
		public String gettexture(){
			return "lightshadow1";
		}
		@Override
		public int getmanacost(){return 20;}


		@Override
		public void  cast(int textureid, Rectangle spellrectin){
			try {
				indo = true;
				spellid =textureid;
				spellrect = spellrectin;
				Gdx.app.postRunnable(new Runnable() {
					@Override
					public void run() {
						texturesarr[spellid] = atlas.findRegion("lightshadow1");
						spellrect.x  = hero.x + 50;
						spellrect.y  = hero.y + 20;
						spellrect.width  = 80;
						spellrect.height  = 80;
					}
				});

				sleep(100);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		@Override
		public void go() {
			try{
				while (spellrect.x < enemy.x-50){
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {
							texturesarr[spellid] = atlas.findRegion("lightshadow1");
							spellrect.x += 5;
						}
					});
					sleep(25);
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {
							texturesarr[spellid] = atlas.findRegion("lightshadow2");
							spellrect.x += 5;
						}
					});
					sleep(25);

				}
				if ((godice(1, 20) + modcontrol) > (5 + enemymoddef)) {
					enemyhealth -= godice(1, 16) + modattack;
				}
				gotoslot();
				enemymodattack-=5;
				enemymoddef-=5;
				enemymodcontrol-=5;
				sleep(4000);
				enemymodattack+=5;
				enemymoddef+=5;
				enemymodcontrol+=5;

			}catch (Exception e) {
				throw new RuntimeException(e);
			}

		}
	}
	class EnemyLightShadow extends  Spell{
		@Override
		public String gettexture(){
			return "lightshadow1";
		}
		@Override
		public int getmanacost(){return 20;}


		@Override
		public void  cast(int textureid, Rectangle spellrectin){
			try {
				indo = true;
				spellid =textureid;
				spellrect = spellrectin;
				Gdx.app.postRunnable(new Runnable() {
					@Override
					public void run() {
						enemytexturesarr[spellid] = atlas.findRegion("lightshadow1");
						spellrect.x  = enemy.x - 50;
						spellrect.y  = enemy.y + 20;
						spellrect.width  = 80;
						spellrect.height  = 80;
					}
				});

				sleep(100);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		@Override
		public void go() {
			try{
				while (spellrect.x > hero.x){
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {
							enemytexturesarr[spellid] = atlas.findRegion("lightshadow1");
							spellrect.x -= 5;
						}
					});
					sleep(25);
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {
							enemytexturesarr[spellid] = atlas.findRegion("lightshadow2");
							spellrect.x -= 5;
						}
					});
					sleep(25);

				}
				if ((godice(1, 20) + enemymodcontrol) > (5 + moddef)) {
					health -= godice(1, 16) + enemymodattack;
				}
				Gdx.app.postRunnable(new Runnable() {
					@Override
					public void run() {
						enemytexturesarr[spellid] = atlas.findRegion("pustota");
					}
				});
				enemymodattack-=5;
				enemymoddef-=5;
				enemymodcontrol-=5;
				sleep(4000);
				enemymodattack+=5;
				enemymoddef+=5;
				enemymodcontrol+=5;
				indo = false;

			}catch (Exception e) {
				throw new RuntimeException(e);
			}

		}
	}


}
