import { Collection, MongoClient} from 'mongodb';
import { Operazioni} from '../ports/Operazioni';
import { Movimento } from '../models/Movimento';

export class MongoAdapter implements Operazioni{
    private url:string;
    private dbName:string;
    private collectionName:string;
    private client:MongoClient;
    constructor(url:string,dbName:string,collectionName:string){
        this.url=url;
        this.dbName=dbName;
        this.collectionName=collectionName;
        this.client=new MongoClient(this.url);
    }
    private async getCollection():Promise<Collection>{
        await this.client.connect();
        return this.client.db(this.dbName).collection(this.collectionName);
    }
    async visualizzaMovimenti(codiceConto: string): Promise<Movimento[]> {
        let coll=await this.getCollection();
        let result=await coll.find({ 'codiceConto': codiceConto }).toArray() as unknown as Movimento[];
        await this.client.close();
        return result;
    }
    async versa(codiceConto:string,importo: number): Promise<void> {
        let coll=await this.getCollection();
        await coll.insertOne(new Movimento(new Date(),new Date(),codiceConto,importo));
    }
    async preleva(codiceConto:string,importo: number): Promise<void> {
        let coll=await this.getCollection();
        await coll.insertOne(new Movimento(new Date(),new Date(),codiceConto,-importo));
    }
    
    

}