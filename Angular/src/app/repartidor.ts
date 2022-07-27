import { Entrega } from "./entrega";

export class Repartidor{

    constructor(
        public id:number,
        public nombre:string,
        public apellido:string,
        public rol:string,
        public vehiculo:string,
        public fechaN:string,
        public direccion:string,
        public email:string,
        public dni:string,
        public contrasena:string,
        public estado:string,
        public zona:string
    ){}
}
