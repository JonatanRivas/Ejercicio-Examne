package configuraciones;

public class Transacciones {

    // Nombre de la base de datos
    public static final String NAME_DATABASE = "DataBasePicture";

    //Creacion de la tabla de imagen
    public static final String TABLE_PICTURE = "picture";

        //Creacion de los atributos
        public static final String PICTURE_ID = "id";
        public static final String PICTURE_NAME = "name";
        public static final String PICTURE_DESCRIPTION = "description";
        public static final String PICTURE_PATH_IMAGE = "path";
        public static final String PICTURE_IMAGE = "image";

        //Creacion de la tabla
        public static final String CREATE_TABLE_PICTURE = "CREATE TABLE " + TABLE_PICTURE +
                "("+
                PICTURE_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                PICTURE_NAME +" TEXT, "+
                PICTURE_DESCRIPTION +" TEXT, "+
                PICTURE_PATH_IMAGE +" TEXT, "+
                PICTURE_IMAGE +" BLOB"+
                ")";



        public static final String DROP_TABLE_PICTURE = "DROP TABLE IF EXIST " + TABLE_PICTURE;






        //Seleccionar todas las personas
        public static final String SELECT_ALL_TABLE_PICTURE = "SELECT * FROM " + TABLE_PICTURE;

}
