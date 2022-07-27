import { Producto } from "./producto";
import { Entrega } from "./entrega";

export class Pedido{

    //public PROD?: Producto[];
    //public Entrega?: Entrega;

    constructor(
        public id:number,
        public monto:number,
        public dniCliente:string,   //porai lo cambiamos a email
        public descripcion:string,
        public nombreCliente:string,
        public calle:string,
        public entreCalles:string,
        public numero:string,
        public departamento:string,
        public latitud:number,
        public longitud:number,
        public observacion:string,
        public entrega:any,
        public idJusta:number,
        public entregado:boolean,
        public productos: Producto[]
    ){}
}
