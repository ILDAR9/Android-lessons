package sdl.example.allyoushouldknow;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class ShapeTest extends Activity {

	class RenderView extends View {
		RenderView(Context context) {
			super(context);
		}

		protected void onDraw(Canvas canvas) {

			Display display = getWindowManager().getDefaultDisplay();
			DisplayMetrics metricsB = new DisplayMetrics();
			display.getMetrics(metricsB);
			float grassl = 0, grasst = metricsB.heightPixels * (float) 0.63125, // 505
			grassr = metricsB.widthPixels, // 600
			grassb = metricsB.heightPixels; // 800

			float skyl = 0, skyt = 0, skyr = metricsB.widthPixels, // 600
			skyb = metricsB.heightPixels * (float) 0.63125; // 505

			float housel = metricsB.heightPixels * (float) 0.025, // 20
			houset = metricsB.heightPixels * (float) 0.40625, // 325
			houser = metricsB.heightPixels * (float) 0.25, // 200
			houseb = metricsB.heightPixels * (float) 0.63125;// 505

			float windl = metricsB.heightPixels * (float) 0.0875, // 70
			windt = metricsB.heightPixels * (float) 0.46875, // 375
			windr = metricsB.heightPixels * (float) 0.1875, // 150
			windb = metricsB.heightPixels * (float) 0.56875; // 455

			float linex = metricsB.heightPixels * (float) 0.1375, // 110
			liney = metricsB.heightPixels * (float) 0.51875; // 415

			float roofX1 = metricsB.heightPixels * (float) 0.0125, // 10
			roofY1 = metricsB.heightPixels * (float) 0.40625, // 325
			roofX2 = metricsB.heightPixels * (float) 0.1375, // 110
			roofY2 = metricsB.heightPixels * (float) 0.28125, // 225
			roofX3 = metricsB.heightPixels * (float) 0.2625, // 210
			roofY3 = metricsB.heightPixels * (float) 0.40625; // 325

			float rsun = metricsB.heightPixels * (float) 0.075, // 60
			xsun = metricsB.heightPixels * (float) 0.475, // 380
			ysun = metricsB.heightPixels * (float) 0.125; // 100

			float cloudl = metricsB.heightPixels * (float) 0.0375, // 30
			cloudt = metricsB.heightPixels * (float) 0.0375, // 30
			cloudr = metricsB.heightPixels * (float) 0.35, // 280
			cloudb = metricsB.heightPixels * (float) 0.175; // 140

			float cloudl2 = metricsB.heightPixels * (float) 0.25, // 200
			cloudt2 = metricsB.heightPixels * (float) 0.2, // 160
			cloudr2 = metricsB.heightPixels * (float) 0.375, // 300
			cloudb2 = metricsB.heightPixels * (float) 0.2625;// 210

			float stalkXl = metricsB.heightPixels * (float) 0.4375, // 350
			stalkYt = metricsB.heightPixels * (float) 0.525, // 420
			stalkXr = metricsB.heightPixels * (float) 0.475, // 380
			stalkYb = metricsB.heightPixels * (float) 0.775; // 620

			float leafr = metricsB.heightPixels * (float) 0.0375, // 30
			leafx = metricsB.heightPixels * (float) 0.4, // 320
			leafy = metricsB.heightPixels * (float) 0.6, // 480
			leafx2 = metricsB.heightPixels * (float) 0.45625, // 365
			leafy2 = metricsB.heightPixels * (float) 0.6375, // 510
			leafx3 = metricsB.heightPixels * (float) 0.5125, // 410
			leafy3 = metricsB.heightPixels * (float) 0.575, // 460
			leafx5 = metricsB.heightPixels * (float) 0.3625, // 290
			leafy5 = metricsB.heightPixels * (float) 0.5375, // 430
			leafx6 = metricsB.heightPixels * (float) 0.55, // 440
			leafy7 = metricsB.heightPixels * (float) 0.475, // 380
			leafy8 = metricsB.heightPixels * (float) 0.43125, // 345
			leafmainx = metricsB.heightPixels * (float) 0.45625, // 365
			leafmainr = metricsB.heightPixels * (float) 0.08125, // 65
			leafmainy = metricsB.heightPixels * (float) 0.525;// 420

			float catx1 = metricsB.heightPixels * (float) 0.45625, // 365
			caty1 = metricsB.heightPixels * (float) 0.5125, // 410
			catr1 = metricsB.heightPixels * (float) 0.0375; // 30

			float catx2 = metricsB.heightPixels * (float) 0.45625, // 365
			caty2 = metricsB.heightPixels * (float) 0.525, // 420
			catr2 = metricsB.heightPixels * (float) 0.01875; // 15

			float catnose_x = metricsB.heightPixels * (float) 0.45625, // 365
			catnose_y = metricsB.heightPixels * (float) 0.5125, // 410
			catnose_r = metricsB.heightPixels * (float) 0.00625; // 5

			float us1_x1 = metricsB.heightPixels * (float) 0.465, // 372
			us1_y1 = metricsB.heightPixels * (float) 0.51875, // 415
			us1_x2 = metricsB.heightPixels * (float) 0.5125, // 410
			us1_y2 = metricsB.heightPixels * (float) 0.50625; // 405

			float us2_y1 = metricsB.heightPixels * (float) 0.525, // 420
			us2_y2 = metricsB.heightPixels * (float) 0.5375; // 430

			float us3_x1 = metricsB.heightPixels * (float) 0.4475, // 358
			us3_x2 = metricsB.heightPixels * (float) 0.4; // 320

			float cateye_x1 = metricsB.heightPixels * (float) 0.46875, // 375
			cateye_x2 = metricsB.heightPixels * (float) 0.44375, // 355
			cateye_y = metricsB.heightPixels * (float) 0.4975, // 398
			cateye_r = metricsB.heightPixels * (float) 0.00625; // 5

			float cateye2_x1 = metricsB.heightPixels * (float) 0.46875, // 375
			cateye2_x2 = metricsB.heightPixels * (float) 0.44375, // 355
			cateye2_y = metricsB.heightPixels * (float) 0.4975, // 398
			cateye2_r = metricsB.heightPixels * (float) 0.00375; // 3

			float catfoot1_x1 = metricsB.heightPixels * (float) 0.475, // 380
			catfoot1_x2 = metricsB.heightPixels * (float) 0.49375, // 395
			catfoot2_x1 = metricsB.heightPixels * (float) 0.4375, // 350
			catfoot2_x2 = metricsB.heightPixels * (float) 0.41875, // 335
			catfoot_y1 = metricsB.heightPixels * (float) 0.55, // 440
			catfoot_y2 = metricsB.heightPixels * (float) 0.5625; // 450

			float ear1_X1 = metricsB.heightPixels * (float) 0.425, // 340
			ear1_X2 = metricsB.heightPixels * (float) 0.425, // 340
			ear1_X3 = metricsB.heightPixels * (float) 0.4475, // 358
			ear1_Y1 = metricsB.heightPixels * (float) 0.4975, // 398
			ear1_Y2 = metricsB.heightPixels * (float) 0.4675, // 374
			ear1_Y3 = metricsB.heightPixels * (float) 0.47875; // 383

			float ear2_X1 = metricsB.heightPixels * (float) 0.4875, // 390
			ear2_X2 = metricsB.heightPixels * (float) 0.4875, // 390
			ear2_X3 = metricsB.heightPixels * (float) 0.465, // 372
			ear2_Y1 = metricsB.heightPixels * (float) 0.4975, // 398
			ear2_Y2 = metricsB.heightPixels * (float) 0.4675, // 374
			ear2_Y3 = metricsB.heightPixels * (float) 0.47875; // 383

			float tr_X1 = metricsB.heightPixels * (float) 0.20625, // 165
			tr_X2 = metricsB.heightPixels * (float) 0.1875, // 150
			tr_X3 = metricsB.heightPixels * (float) 0.1875, // 150
			tr_X4 = metricsB.heightPixels * (float) 0.20625, // 165
			tr_Y1 = metricsB.heightPixels * (float) 0.35, // 280
			tr_Y2 = metricsB.heightPixels * (float) 0.33125, // 265
			tr_Y3 = metricsB.heightPixels * (float) 0.25, // 200
			tr_Y4 = metricsB.heightPixels * (float) 0.25; // 200

			Paint paintbg = new Paint();
			Paint paint = new Paint();
			Paint paintw = new Paint();
			Paint paintl = new Paint();
			Paint paintkr = new Paint();
			Paint paintbg2 = new Paint();
			Paint psun = new Paint();
			Paint pcloud = new Paint();
			Paint ptree = new Paint();
			Paint pnose = new Paint();

			paintkr.setStyle(Paint.Style.FILL);
			paintkr.setAntiAlias(true);
			paintl.setAntiAlias(true);
			paintl.setStyle(Paint.Style.FILL);

			Path path = new Path();
			path.setFillType(Path.FillType.EVEN_ODD);
			path.moveTo(roofX1, roofY1);
			path.lineTo(roofX2, roofY2);
			path.lineTo(roofX3, roofY3);
			path.lineTo(roofX1, roofY1);
			path.close();

			Path path1 = new Path();
			path1.setFillType(Path.FillType.EVEN_ODD);
			path1.moveTo(ear1_X1, ear1_Y1);
			path1.lineTo(ear1_X2, ear1_Y2);
			path1.lineTo(ear1_X3, ear1_Y3);
			path1.lineTo(ear1_X1, ear1_Y1);
			path1.close();

			Path path2 = new Path();
			path2.setFillType(Path.FillType.EVEN_ODD);
			path2.moveTo(ear2_X1, ear2_Y1);
			path2.lineTo(ear2_X2, ear2_Y2);
			path2.lineTo(ear2_X3, ear2_Y3);
			path2.lineTo(ear2_X1, ear2_Y1);
			path2.close();

			Path path3 = new Path();
			path3.setFillType(Path.FillType.EVEN_ODD);
			path3.moveTo(tr_X1, tr_Y1);
			path3.lineTo(tr_X2, tr_Y2);
			path3.lineTo(tr_X3, tr_Y3);
			path3.lineTo(tr_X4, tr_Y4);
			path3.lineTo(tr_X1, tr_Y1);
			path3.close();

			RectF oval = new RectF(cloudl, cloudt, cloudr, cloudb);
			RectF oval2 = new RectF(cloudl2, cloudt2, cloudr2, cloudb2);
			RectF oval3 = new RectF(catfoot1_x1, catfoot_y1, catfoot1_x2,
					catfoot_y2);
			RectF oval4 = new RectF(catfoot2_x1, catfoot_y1, catfoot2_x2,
					catfoot_y2);

			paintbg.setARGB(255, 0, 204, 51);
			paintbg2.setARGB(255, 102, 204, 255);
			paint.setARGB(255, 204, 153, 51);
			paintw.setARGB(255, 255, 255, 255);
			paintl.setARGB(255, 0, 0, 0);
			paintkr.setARGB(255, 153, 102, 51);
			psun.setARGB(255, 255, 255, 51);
			pcloud.setARGB(255, 255, 255, 255);
			ptree.setARGB(255, 51, 153, 51);
			pnose.setARGB(255, 255, 153, 153);
			// paintkr.setStyle(Paint.Style.FILL);

			canvas.drawRect(grassl, grasst, grassr, grassb, paintbg);
			canvas.drawRect(skyl, skyt, skyr, skyb, paintbg2);
			canvas.drawRect(housel, houset, houser, houseb, paint);
			canvas.drawRect(windl, windt, windr, windb, paintw);
			canvas.drawLine(linex, windt, linex, windb, paintl);
			canvas.drawLine(windl, liney, windr, liney, paintl);
			canvas.drawPath(path, paintkr);
			canvas.drawCircle(xsun, ysun, rsun, psun);
			canvas.drawOval(oval, pcloud);
			canvas.drawOval(oval2, pcloud);
			canvas.drawRect(stalkXl, stalkYt, stalkXr, stalkYb, paintkr);
			canvas.drawCircle(leafx, leafy, leafr, ptree);
			canvas.drawCircle(leafx2, leafy2, leafr, ptree);
			canvas.drawCircle(leafx3, leafy, leafr, ptree);
			canvas.drawCircle(leafx2, leafy3, leafr, ptree);
			canvas.drawCircle(leafx5, leafy5, leafr, ptree);
			canvas.drawCircle(leafx6, leafy5, leafr, ptree);
			canvas.drawCircle(leafx, leafy7, leafr, ptree);
			canvas.drawCircle(leafx3, leafy7, leafr, ptree);
			canvas.drawCircle(leafx2, leafy8, leafr, ptree);
			canvas.drawCircle(leafmainx, leafmainy, leafmainr, ptree);
			canvas.drawCircle(catx1, caty1, catr1, paintl);
			canvas.drawCircle(catx2, caty2, catr2, pcloud);
			canvas.drawCircle(catnose_x, catnose_y, catnose_r, pnose);
			canvas.drawLine(us1_x1, us1_y1, us1_x2, us1_y2, paintl);
			canvas.drawLine(us1_x1, us2_y1, us1_x2, us2_y2, paintl);
			canvas.drawLine(us3_x1, us1_y1, us3_x2, us1_y2, paintl);
			canvas.drawLine(us3_x1, us2_y1, us3_x2, us2_y2, paintl);
			canvas.drawCircle(cateye_x1, cateye_y, cateye_r, pcloud);
			canvas.drawCircle(cateye_x2, cateye_y, cateye_r, pcloud);
			canvas.drawCircle(cateye2_x1, cateye2_y, cateye2_r, paintl);
			canvas.drawCircle(cateye2_x2, cateye2_y, cateye2_r, paintl);
			canvas.drawOval(oval3, paintl);
			canvas.drawOval(oval4, paintl);
			canvas.drawPath(path1, paintl);
			canvas.drawPath(path2, paintl);
			canvas.drawPath(path3, paintl);
			// invalidate();

			if (metricsB.widthPixels > 1000) {

				float house2l = 820, house2t = 425, house2r = 1000, house2b = 605;
				float roof2X1 = 810, roof2Y1 = 425, roof2X2 = 910, roof2Y2 = 325, roof2X3 = 1010, roof2Y3 = 425;
				float wind2l = 870, wind2t = 475, wind2r = 950, wind2b = 555;
				float l2x = 910, l2y = 515;
				float tr2_X1 = 965, tr2_X2 = 950, tr2_X3 = 950, tr2_X4 = 965, tr2_Y1 = 380, tr2_Y2 = 365, tr2_Y3 = 300, tr2_Y4 = 300;

				float cloudl3 = 630, cloudt3 = 130, cloudr3 = 880, cloudb3 = 240;

				Path pathroof2 = new Path();
				pathroof2.setFillType(Path.FillType.EVEN_ODD);
				pathroof2.moveTo(roof2X1, roof2Y1);
				pathroof2.lineTo(roof2X2, roof2Y2);
				pathroof2.lineTo(roof2X3, roof2Y3);
				pathroof2.lineTo(roof2X1, roof2Y1);
				pathroof2.close();

				Path pathtr2 = new Path();
				pathtr2.setFillType(Path.FillType.EVEN_ODD);
				pathtr2.moveTo(tr2_X1, tr2_Y1);
				pathtr2.lineTo(tr2_X2, tr2_Y2);
				pathtr2.lineTo(tr2_X3, tr2_Y3);
				pathtr2.lineTo(tr2_X4, tr2_Y4);
				pathtr2.lineTo(tr2_X1, tr2_Y1);
				pathtr2.close();

				RectF oval5 = new RectF(cloudl3, cloudt3, cloudr3, cloudb3);

				canvas.drawRect(house2l, house2t, house2r, house2b, paint);
				canvas.drawPath(pathroof2, paintkr);
				canvas.drawRect(wind2l, wind2t, wind2r, wind2b, paintw);
				canvas.drawLine(l2x, wind2t, l2x, wind2b, paintl);
				canvas.drawLine(wind2l, l2y, wind2r, l2y, paintl);
				canvas.drawPath(pathtr2, paintl);
				canvas.drawOval(oval5, pcloud);
			}

		}
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(new RenderView(this));

	}
}
