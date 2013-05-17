package com.example.sqlite;



import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class SQLiteActivity extends Activity implements OnClickListener{
	ImageView principal, adobo, ceviche, chicharon, chifa;
	EditText nombre, telefono, ebuscar;
	Button insertar, ver, buscar, editar, eliminar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		nombre = (EditText) findViewById(R.id.edNombre);
		telefono = (EditText) findViewById(R.id.edTelefono);
		insertar = (Button) findViewById(R.id.btInsertar);
		ebuscar = (EditText) findViewById(R.id.etBuscar);
		buscar = (Button) findViewById(R.id.btBuscar);
		editar = (Button) findViewById(R.id.btEditar);
		eliminar = (Button) findViewById(R.id.btEliminar);
		ver = (Button) findViewById(R.id.btVer);
		
		insertar.setOnClickListener(this);
		ver.setOnClickListener(this);
		buscar.setOnClickListener(this);
		editar.setOnClickListener(this);
		eliminar.setOnClickListener(this);
		
		// Para las imagenes
		principal = (ImageView) findViewById(R.id.ivPrincipal);
		adobo = (ImageView) findViewById(R.id.ivAdobo);
		ceviche = (ImageView) findViewById(R.id.ivCeviche);
		chicharon = (ImageView) findViewById(R.id.ivChicharon);
		chifa = (ImageView) findViewById(R.id.ivChifa);
		
		adobo.setOnClickListener(this);
		ceviche.setOnClickListener(this);
		chicharon.setOnClickListener(this);
		chifa.setOnClickListener(this);
		
	}
	
	public void onClick(View v){
		switch(v.getId()){
		case R.id.ivAdobo:
			principal.setImageResource(R.drawable.adobo);
			Long lba = Long.parseLong("4");
			
			Telefonos tela = new Telefonos(this);
			try {
				tela.abrir();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String bNa = tela.getN(lba);
			String bTa = tela.getT(lba);
			tela.cerrar();
			
			nombre.setText(bNa);
			telefono.setText(bTa);
			break;
		case R.id.ivCeviche:
			principal.setImageResource(R.drawable.ceviche);
			Long lbc = Long.parseLong("7");
			
			Telefonos telc = new Telefonos(this);
			try {
				telc.abrir();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String bNc = telc.getN(lbc);
			String bTc = telc.getT(lbc);
			telc.cerrar();
			
			nombre.setText(bNc);
			telefono.setText(bTc);
			break;
		case R.id.ivChicharon:
			principal.setImageResource(R.drawable.chicharon);
			
			Long lbch = Long.parseLong("5");
			
			Telefonos telch = new Telefonos(this);
			try {
				telch.abrir();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String bNch = telch.getN(lbch);
			String bTch = telch.getT(lbch);
			telch.cerrar();
			
			nombre.setText(bNch);
			telefono.setText(bTch);
			break;
		case R.id.ivChifa:
			principal.setImageResource(R.drawable.chifa);
			
			Long lbci = Long.parseLong("6");
			
			Telefonos telci = new Telefonos(this);
			try {
				telci.abrir();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String bNci = telci.getN(lbci);
			String bTci = telci.getT(lbci);
			telci.cerrar();
			
			nombre.setText(bNci);
			telefono.setText(bTci);
			break;
		case R.id.btInsertar:
			
			boolean funciona = true;
			try{
			String nom = nombre.getText().toString();
			String tel = telefono.getText().toString();
			
			nombre.setText("");
			telefono.setText("");
			
			Telefonos entrada = new Telefonos(SQLiteActivity.this);
			entrada.abrir();
			entrada.crearEntrada(nom, tel);
			entrada.cerrar();
			}catch(Exception e){
				funciona = false;
				String error = e.toString();
				Dialog d = new Dialog (this);
				d.setTitle("No funciona");
				TextView tv = new TextView (this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();
			}finally{
			if(funciona){
				Dialog d = new Dialog(this);
				d.setTitle("funciona?");
				TextView tv = new TextView(this);
				tv.setText("funciona!!");
				d.setContentView(tv);
				d.show();
				}
			}
			break;
		
		case R.id.btVer:
			Intent i = new Intent("com.example.sqlite.SQLVista");
			startActivity(i);
			break;
		
		case R.id.btBuscar:
			String b = ebuscar.getText().toString();
			Long lb = Long.parseLong(b);
			
			Telefonos tel = new Telefonos(this);
			try {
				tel.abrir();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String bN = tel.getN(lb);
			String bT = tel.getT(lb);
			tel.cerrar();
			
			nombre.setText(bN);
			telefono.setText(bT);
			
			break;
		
			
		case R.id.btEditar:
			try{
				
			String eNom = nombre.getText().toString();
			String eTel = telefono.getText().toString();
			String eFila = ebuscar.getText().toString();
			long eFilal = Long.parseLong(eFila);
			
			Telefonos editar = new Telefonos (this);
			editar.abrir();
			editar.editar(eFilal,eNom,eTel);
			editar.cerrar();
			}catch(Exception e){
				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("No funcionooo!");
				TextView tv = new TextView (this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();
			}
			break;
			
		case R.id.btEliminar:
			try{
			String elFila = ebuscar.getText().toString();
			long elFilal = Long.parseLong(elFila);
			
			Telefonos borrar = new Telefonos(this);
			borrar.abrir();
			borrar.borrar(elFilal);
			borrar.cerrar();
			}catch(Exception e){
				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("No funcionooo!");
				TextView tv = new TextView (this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();
			}
			
			break;	
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
