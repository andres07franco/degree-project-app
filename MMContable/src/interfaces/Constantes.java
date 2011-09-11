package interfaces;

public interface Constantes {

    public static final int SE_INICIO_CAJA = 0;
    public static final int NO_SE_INICIO_CAJA = 1;

    /*Estados de los objetos */    
    public static final int ESTADO_INACTIVO = 1;
    public static final int ESTADO_ACTIVO = 2;
    public static final int ESTADO_GUARDADO = 2; /* Indica que el documento(Saldos inicales) solo esta alamcenado*/
    public static final int ESTADO_INICIADO = 3; /*indica que el documneto(saldos iniciales) se han inicado*/

    /*tipos de terceros*/
    public static final int TERCERO = 1;
    public static final int CLIENTE = 2;
    public static final int PROVEESOR = 3;


    public static final int FORMULARIO_NO_APTO_PARA_GUARDAR = 0;
    public static final int FORMULARIO_APTO_PARA_GUARDAR = 0;
    public static final int ESTADO_EDICION = 1;
    public static final int ESTADO_CREACION = 0;
    public static final int ESTADO_SOLO_LECTURA = 3; /*ESTE ESTADO ESTADO ES PARA VISUALIZAR LAS FCTURAS, PUES ESTAS NO SE PUEDEN EDITAR*/
    public static final int PASO_BIENVENIDO = 0;
    public static final int PASO_FORMULARIO_EMPRESA = 1;
    public static final int PASO_FORMULARIO_USUARIO = 2;
    public static final int PASO_FINALIZAR = 3;
    public static final int PASO_ANTERIOR = 0;
    public static final int PASO_SIGUIENTE = 1;
    public static final int USUARIO_ADMINISTRADOR = 1;
    public static final int ESTADO_EMPRESA_ACTIVO = 1;
    public static final int ESTADO_EMPRESA_INACTIVO = 2;
    public static final int ADICIONAR_CON_LECTOR = 0;
    public static final int ADICIONAR_CON_BOTON = 1;


    /*Estados de documentos*/
    public static final String ESTADO_DOCUMENTO_ANULADO  = "A";
    public static final String ESTADO_DOCUMENTO_PAGADO   = "P";
    public static final String ESTADO_DOCUMENTO_DEBE     = "D";
    public static final String ESTADO_DOCUMENTO_INICIADO = "I";
    public static final String ESTADO_DOCUMENTO_GUARDADO = "G";



    /*Banderas para tipo de documento*/
    public static final int DOCUMENTO_SALDOS_INICIALES  = 1;
    public static final int DOCUMENTO_FACTURA_COMPRA    = 2;
    public static final int DOCUMENTO_FACTURA_VENTA     = 3;
    public static final int DOCUMENTO_COTIZACION        = 4;
    public static final int DOCUMENTO_ABONO_A_FACTURA   = 5;
    public static final int DOCUMENTO_INGRESO           = 6;
    public static final int DOCUMENTO_EGRESO            = 7;
    public static final int DOCUMENTO_DEVOLUCION        = 8;


    /*bandera para funcionalidad en saldo iniciales y factura de venta*/
    public static final int ADICIONANDO_ITEMS = 1;
    public static final int EDITANDO_ITEMS = 2;

    /*Id tercero por defecto*/
    public static final Integer TERCERO_POR_DEFECTO = 1; /*es el mimo cliente por mostrador*/

     /*Numero documento saldos iniciales*/
     public static final int NUMERO_SALDOS_INICIALES = 1;
     public static final double IVA = 0.0;

     /*Tipod de Pagos facturas*/
     public static final int TIPO_PAGO_DEBITO = 1;
     public static final int TIPO_PAGO_CREDITO = 2;
     public static final int TIPO_PAGO_PAGADO = 3;


}
