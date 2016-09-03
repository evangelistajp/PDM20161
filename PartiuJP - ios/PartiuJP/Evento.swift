//
//  Evento.swift
//  
//
//  Created by admin on 30/08/16.
//  Copyright © 2016 admin. All rights reserved.
//

import Foundation

class Evento: NSObject, NSCoding {
    var nome:String!
    var local:String!
    //var data:NSDate!
    var valor:Int!
    
    
    override var description: String{
        return self.nome + " - " + self.local //+ " - " + self.preco + " - " + self.data
       
    }
   
    
    init (nome:String, local:String, valor:Int){
        //data:NSDate, ){
        self.nome = nome
        self.local = local
        //self.data = data
        self.valor = valor
    }
    
    required init?(coder aDecoder:NSCoder){
        self.nome = aDecoder.decodeObjectForKey("nome") as! String
        self.local = aDecoder.decodeObjectForKey("local") as! String
        //self.data = aDecoder.decodeObjectForKey("data") as! NSDate
        self.valor = aDecoder.decodeIntegerForKey("valor")
    }
    
    func encodeWithCoder(aCoder: NSCoder){
        aCoder.encodeObject(self.nome, forKey:"nome")
        aCoder.encodeObject(self.local, forKey:"local")
        //aCoder.encodeObject(self.data, forKey:"data")
        aCoder.encodeInteger(self.valor, forKey: "valor")
    }
    
 
}