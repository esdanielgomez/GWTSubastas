package com.Subastas.client;
import java.util.ArrayList;

import com.Subastas.client.DTO.CategoriaDTO;
import com.Subastas.client.DTO.OfertaDTO;
import com.Subastas.client.DTO.SubastaDTO;
import com.Subastas.client.DTO.UsuarioDTO;
import com.Subastas.model.VO.Categoria;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.smartgwt.client.util.SC;
import java.util.List;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Subastas implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network " + "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

	/**
	 * This is the entry point method.
	 */
	
	DialogBox boxVentana = new DialogBox();
	VerticalPanel contenidoCrearUsuario = new VerticalPanel();
	VerticalPanel contenidoVentanaSubasta = new VerticalPanel();
	
	Button btnCancelar = new Button("Cancelar");
	Button btnRegistrar = new Button("Registrar");
	
	TextBox textIdUsuario = new TextBox();
	TextBox textNombreUsuario = new TextBox();
	TextBox textCorreoUsuario = new TextBox();
	
	TextBox textIdSubasta = new TextBox();
	TextBox textDescripcionSubasta = new TextBox();
	ListBox categoriasSubasta = new ListBox();
	TextBox textValorInicialSubasta = new TextBox();
	
	VerticalPanel contenidoVentanaOferta = new VerticalPanel();
	TextBox textIdOferta = new TextBox();
	TextBox textDescripcionOferta = new TextBox();
	TextBox textValorMaximoOferta = new TextBox();
	
	public void crearUsuario(){
		
		boxVentana.setText("Crear Persona");
		boxVentana.setAnimationEnabled(true);
		
		textIdUsuario = new TextBox();
		textNombreUsuario = new TextBox();
		textCorreoUsuario = new TextBox();
		
		contenidoCrearUsuario = new VerticalPanel();
		contenidoCrearUsuario.add(new HTML("Identificacion: "));
		contenidoCrearUsuario.add(textIdUsuario);
		contenidoCrearUsuario.add(new HTML("Nombre: "));
		contenidoCrearUsuario.add(textNombreUsuario);
		contenidoCrearUsuario.add(new HTML("Correo: "));
		contenidoCrearUsuario.add(textCorreoUsuario);
		
		btnCancelar.setStyleName("botonEspecial");
		btnRegistrar.setStyleName("botonEspecial");
		
		contenidoCrearUsuario.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
		contenidoCrearUsuario.add(btnCancelar);
		contenidoCrearUsuario.add(btnRegistrar);
		boxVentana.setWidget(contenidoCrearUsuario);
		
		btnRegistrar.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				UsuarioDTO usuario = new UsuarioDTO(textIdUsuario.getText(), textCorreoUsuario.getText(),textNombreUsuario.getText());
				greetingService.crearUsuario(usuario, callback);
				crearUsuario.setEnabled(true);
				crearOferta.setEnabled(true);
				crearSubasta.setEnabled(true);
				boxVentana.setVisible(false);
			}
		});
		
		btnCancelar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				crearUsuario.setEnabled(true);
				crearOferta.setEnabled(true);
				crearSubasta.setEnabled(true);
				boxVentana.setVisible(false);
			}
		});
	}
	
	final AsyncCallback<String> callback = new AsyncCallback<String>() {
		public void onFailure(Throwable caught) {
			SC.say(caught.toString());
			
		}

		public void onSuccess(String result) {
			SC.say(result);

		}
	};
	
	final AsyncCallback<ArrayList<ArrayList<String>>> callbackCategorias = new AsyncCallback<ArrayList<ArrayList<String>>>() {
		public void onFailure(Throwable caught) {
			SC.say(caught.toString());
			
		}

		@Override
		public void onSuccess(ArrayList<ArrayList<String>> result) {
			
			for(int x=0;x<result.size();x++) {
				  ArrayList<String> fila = result.get(x);
				  categoriasSubasta.addItem(fila.get(1), fila.get(0));
			}
		}
	};
	
	private static class Productos {
	    private final String idSubasta;
	    private final String descripcion;
	    private final String valorInicial;
	    private final String valorPuja;
	    private final String idUsuario;

	    public Productos(String idSubasta, String descripcion, String valorInicial, String valorPuja, String idUsuario) {
	      this.idSubasta = idSubasta;
	      this.descripcion = descripcion;
	      this.valorInicial = valorInicial;
	      this.valorPuja = valorPuja;
	      this.idUsuario = idUsuario;
	    }
	  }
	final AsyncCallback<ArrayList<ArrayList<String>>> callbackSubastas = new AsyncCallback<ArrayList<ArrayList<String>>>() {
		public void onFailure(Throwable caught) {
			SC.say("Lo sentimos, algo salio mal.");
		}
		@Override
		public void onSuccess(ArrayList<ArrayList<String>> result) {
			
			crearUsuario.setEnabled(true);
			crearOferta.setEnabled(true);
			crearSubasta.setEnabled(true);
			boxVentana.setVisible(false);
			boxVentana.setText("Ofertar");
			boxVentana.setAnimationEnabled(true);
			
			CellTable<Productos> table = new CellTable<Productos>();
	        
			TextColumn<Productos> idSubasta = new TextColumn<Productos>() {
			      public String getValue(Productos object) {return object.idSubasta;}
			};
			
			TextColumn<Productos> descripcion = new TextColumn<Productos>() {
			      public String getValue(Productos object) {return object.descripcion;}
			};
			
			TextColumn<Productos> valorInicial = new TextColumn<Productos>() {
			      public String getValue(Productos object) {return object.valorInicial;}
			};
			
			TextColumn<Productos> valorPuja = new TextColumn<Productos>() {
			      public String getValue(Productos object) {return object.valorPuja;}
			};
			
			TextColumn<Productos> usuario = new TextColumn<Productos>() {
			      public String getValue(Productos object) {return object.idUsuario;}
			};
			
			table.addColumn(idSubasta, "IdSubasta");
			table.addColumn(descripcion, "Descripcion");
			table.addColumn(valorInicial, "Valor Inicial");
			table.addColumn(valorPuja, "Maximo ofertado");
			table.addColumn(usuario, "Correo del Subastador");
			
			List<Productos> lista = new ArrayList<>();
	        //lista.add(new Productos("01", "Celular", "10", "15"));
	        //lista.add(new Productos("02", "Cel", "10", "15"));
	        
	        for(int x=0;x<result.size();x++) {
				  ArrayList<String> fila = result.get(x);
				  lista.add(new Productos(fila.get(0), fila.get(2), fila.get(1), fila.get(5), fila.get(3)));
			}
	        
			table.setRowCount(lista.size(), true);
			table.setRowData(0, lista);

			VerticalPanel contenido = new VerticalPanel();
			contenido.add(new HTML("<p align='center'>"));
			contenido.add(new HTML("Identificacion: "));
			contenido.add(textIdOferta);
			contenido.add(new HTML("Valor a ofertar: "));
			contenido.add(textValorMaximoOferta);
			contenido.add(new HTML("Productos: "));
			contenido.add(table);
			
			textIdOferta.setEnabled(false);
			
			btnCancelar.setStyleName("botonEspecial");
			//btnRegistrar.setStyleName("botonEspecial");
			
			contenido.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
			contenido.add(btnCancelar);
			//contenido.add(btnRegistrar);
			boxVentana.setWidget(contenido);
			boxVentana.setVisible(true);
			
			// Add a selection model to handle user selection.
		    final SingleSelectionModel<Productos> selectionModel = new SingleSelectionModel<Productos>();
		    table.setSelectionModel(selectionModel);
		    selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
		      public void onSelectionChange(SelectionChangeEvent event) {
		        Productos selected = selectionModel.getSelectedObject();
		        if (selected != null) {
		        	if(Window.confirm("Estas seguro de que quieres ofertar por: $" + textValorMaximoOferta.getText().toString()+ ", el producto: " + selected.descripcion)){
			        	
		        		//if(textIdOferta.getText().toString().equals(selected.idUsuario)){
		        		//	SC.say("Lo sentimos, no puede ofertar a su propia subasta.");
		        		//}
		        		{
		        			OfertaDTO oferta = new OfertaDTO(null, textValorMaximoOferta.getText(), new SubastaDTO(selected.idSubasta,null,null,null,null,null), new UsuarioDTO(textIdOferta.getText(),null,null));
		        			greetingService.crearOferta(oferta, callbackOfertar);
				        	crearUsuario.setEnabled(true);
				        	  crearOferta.setEnabled(true);
				        	  crearSubasta.setEnabled(true);
				        	  boxVentana.setVisible(false);
		        		}
		        		
		        		  
		          }
		        }
		      }
		    });
			
			btnCancelar.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					crearUsuario.setEnabled(true);
					crearOferta.setEnabled(true);
					crearSubasta.setEnabled(true);
					boxVentana.setVisible(false);
				}
			});
		}
	};
	
	final AsyncCallback<String> callbackOfertar = new AsyncCallback<String>() {

		@Override
		public void onFailure(Throwable caught) {
			SC.say("Lo sentimos, algo salio mal.");
		}

		@Override
		public void onSuccess(String result) {
			SC.say(result);

		}
	};
	final AsyncCallback<String> callbackBuscar = new AsyncCallback<String>() {

		@Override
		public void onFailure(Throwable caught) {
			SC.say("Lo sentimos, algo salio mal.");
		}

		@Override
		public void onSuccess(String result) {
			if(result!=null){
				SC.say(result);
			}
			else{
				OfertaDTO oferta = new OfertaDTO();
				oferta.setValorPuja(textValorMaximoOferta.getText());
				oferta.setSubasta(new SubastaDTO());
				oferta.getSubasta().setCategoria(new CategoriaDTO(categoriasSubasta.getSelectedValue().toString(),null));
				oferta.getSubasta().setDescripcion(textDescripcionOferta.getText().toString());
				greetingService.getSubastasOferta(oferta, callbackSubastas);
			}
		}
	};
	
	public void crearSubasta(){
		
		//boxVentana = new DialogBox();
		boxVentana.setText("Subastar un producto");
		boxVentana.setAnimationEnabled(true);
		//boxSubasta.setPixelSize(300, 300);
		
		contenidoVentanaSubasta = new VerticalPanel();
		textIdSubasta = new TextBox();
		textDescripcionSubasta = new TextBox();
		categoriasSubasta = new ListBox();
		
		greetingService.getCategorias(callbackCategorias);
		
		textValorInicialSubasta = new TextBox();
		textValorInicialSubasta.setText("1");
		
		contenidoVentanaSubasta.add(new HTML("Su identificacion: "));
		contenidoVentanaSubasta.add(textIdSubasta);
		contenidoVentanaSubasta.add(new HTML("Descripcion del producto: "));
		contenidoVentanaSubasta.add(textDescripcionSubasta);
		contenidoVentanaSubasta.add(new HTML("Valor inicial: "));
		contenidoVentanaSubasta.add(textValorInicialSubasta);
		contenidoVentanaSubasta.add(new HTML("Categoria: "));
		contenidoVentanaSubasta.add(categoriasSubasta);
		
		Button btnCancelar = new Button("Cancelar");
		Button btnRegistrar = new Button("Subastar");
		
		btnCancelar.setStyleName("botonEspecial");
		btnRegistrar.setStyleName("botonEspecial");
		
		contenidoVentanaSubasta.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
		contenidoVentanaSubasta.add(btnCancelar);
		contenidoVentanaSubasta.add(btnRegistrar);
		boxVentana.setWidget(contenidoVentanaSubasta);
	
		//RootPanel.get("crearUsuario").add(boxVentana);
		
		btnRegistrar.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				try{
					SubastaDTO subasta = new SubastaDTO(null, textDescripcionSubasta.getText().toString(), textValorInicialSubasta.getText().toString(), new CategoriaDTO(categoriasSubasta.getSelectedValue().toString(),null), new UsuarioDTO(textIdSubasta.getText().toString(),null,null), null);
					greetingService.crearSubasta(subasta, callback);
				}
				catch(Error e){
					SC.say(e.getMessage());
				}
				catch(Exception e){
					SC.say("Lo sentimos, algo salio mal. " + e.getMessage());
				}
				
				crearUsuario.setEnabled(true);
				crearOferta.setEnabled(true);
				crearSubasta.setEnabled(true);
				boxVentana.setVisible(false);
			}
		});
		
		btnCancelar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				crearUsuario.setEnabled(true);
				crearOferta.setEnabled(true);
				crearSubasta.setEnabled(true);
				boxVentana.setVisible(false);
			}
		});
	}

	public void buscarSubasta(){
	
		//boxVentana = new DialogBox();
		boxVentana.setText("Buscar productos");
		boxVentana.setAnimationEnabled(true);
		
		contenidoVentanaOferta = new VerticalPanel();
		textIdOferta = new TextBox();
		textDescripcionOferta = new TextBox();
		textValorMaximoOferta = new TextBox();
		
		categoriasSubasta = new ListBox();
		greetingService.getCategorias(callbackCategorias);
		
		
		contenidoVentanaOferta.add(new HTML("Categoria: "));
		contenidoVentanaOferta.add(categoriasSubasta);
		contenidoVentanaOferta.add(new HTML("Descripcion del producto: "));
		contenidoVentanaOferta.add(textDescripcionOferta);
		contenidoVentanaOferta.add(new HTML("Valor maximo: "));
		contenidoVentanaOferta.add(textValorMaximoOferta);
		contenidoVentanaOferta.add(new HTML("Su identificacion: "));
		contenidoVentanaOferta.add(textIdOferta);
		
		Button btnCancelar = new Button("Cancelar");
		Button btnBuscar = new Button("Buscar");
		Button btnOfertar = new Button("Ofertar");
		
		btnCancelar.setStyleName("botonEspecial");
		btnBuscar.setStyleName("botonEspecial");
		btnOfertar.setVisible(false);
		contenidoVentanaOferta.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
		contenidoVentanaOferta.add(btnCancelar);
		contenidoVentanaOferta.add(btnBuscar);
		contenidoVentanaOferta.add(btnOfertar);
		boxVentana.setWidget(contenidoVentanaOferta);
	
		//RootPanel.get("crearUsuario").add(boxVentana);
		
		btnBuscar.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				OfertaDTO oferta = new OfertaDTO();
				oferta.setSubasta(new SubastaDTO());
				oferta.setUsuario(new UsuarioDTO());
				oferta.getUsuario().setIdUsuario(textIdOferta.getText());
				oferta.setValorPuja(textValorMaximoOferta.getText());
				oferta.getSubasta().setDescripcion(textDescripcionOferta.getText());
				oferta.getSubasta().setCategoria(new CategoriaDTO());
				oferta.getSubasta().getCategoria().setIdCategoria(categoriasSubasta.getSelectedValue().toString());
				greetingService.buscarSubasta(oferta, callbackBuscar);
				
			}
		});
		
		btnCancelar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				boxVentana.setVisible(false);
				crearUsuario.setEnabled(true);
				crearOferta.setEnabled(true);
				crearSubasta.setEnabled(true);
				
			}
		});
	}

	final Button crearUsuario = new Button("Crear usuario");
	final Button crearSubasta = new Button("   Subastar  ");
	final Button crearOferta = new Button("    Ofertar   ");
	
	public void onModuleLoad() {
		
		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel.get("menuCrearUsuario").add(crearUsuario);
		RootPanel.get("menuCrearSubasta").add(crearSubasta);
		RootPanel.get("menuCrearOferta").add(crearOferta);

		RootPanel.get("crearUsuario").add(boxVentana);
		
		boxVentana.setVisible(false);
		
		crearUsuario.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				crearUsuario();
				crearUsuario.setEnabled(false);
				crearOferta.setEnabled(false);
				crearSubasta.setEnabled(false);
				boxVentana.setVisible(true);
				
			}
		});
		
		crearSubasta.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				crearSubasta();
				crearUsuario.setEnabled(false);
				crearOferta.setEnabled(false);
				crearSubasta.setEnabled(false);
				boxVentana.setVisible(true);
			}
		});
		
		crearOferta.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				buscarSubasta();
				crearUsuario.setEnabled(false);
				crearOferta.setEnabled(false);
				crearSubasta.setEnabled(false);
				boxVentana.setVisible(true);
			}
		});
		
	}
}
