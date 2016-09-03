//
//  CelulaTableViewCell.swift
//  Gostei
//
//  Created by admin on 19/08/16.
//  Copyright © 2016 admin. All rights reserved.
//

import UIKit

class CelulaTableViewCell: UITableViewCell {
    
    
    @IBOutlet weak var lbnome: UILabel!
    
    
    
    var ev:Evento!
    

    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        if(selected == true){
            print(String(ev))
            let alerta = UIAlertController(title: "Evento", message: ev.nome, preferredStyle: UIAlertControllerStyle.Alert)
            alerta.addAction(UIAlertAction(title: "ok", style: UIAlertActionStyle.Default, handler: nil))
            
            self.window?.rootViewController?.presentViewController(alerta, animated: true, completion: nil)
            
        }
    }
    

}
