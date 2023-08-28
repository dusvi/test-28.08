export class Movimento{
    dataMovimento:Date;
    dataContabile:Date;
    codiceConto:string;
    tipoMovimento:string;
    importo:number;
    constructor(dataMovimento:Date,dataContabile:Date,codiceConto:string,importo:number){
        this.dataMovimento=dataMovimento;
        if(dataContabile>dataMovimento){
            this.dataContabile=dataContabile;
        }else{
            this.dataContabile=dataMovimento;
        }
        this.codiceConto=codiceConto;
        this.importo=importo;
        if(importo>=0){
            this.tipoMovimento='versamento';
        }else{
            this.tipoMovimento='prelievo';
        }
    }
}