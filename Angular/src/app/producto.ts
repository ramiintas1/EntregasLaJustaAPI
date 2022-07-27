export class Producto{

    constructor(
        public id:number,
        public nombre:string,
        public peso:number,
        public descripcion:string,
        public valor:number,
        public frio:boolean,
        public marca:string,
        public productor:string,
        public idPedido:number,
        public stock:number
    ){}
}