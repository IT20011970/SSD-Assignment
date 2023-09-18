import {Component, OnInit} from '@angular/core';
import {FarmerService} from "../../../../_service/farmer.service";
import {environment} from "../../../../../environments/environment";
import {DomSanitizer} from "@angular/platform-browser";
import {Router} from "@angular/router";

@Component({
  selector: 'app-farmer-items-view',
  templateUrl: './farmer-items-view.component.html',
  styleUrls: ['./farmer-items-view.component.css']
})
export class FarmerItemsViewComponent implements OnInit {

  items = [];

  constructor(private farmerS: FarmerService, private sanitizer: DomSanitizer, private router: Router) {
  }

  ngOnInit(): void {
    this.getItems();
  }

  getItems() {
    this.farmerS.getItems().subscribe(items => {
      console.log(items)
      this.items = items;
    })
  }

  setItem(item) {
    this.farmerS.item = item;
    this.router.navigate(['/main/farmer/manage_items'])
  }

  getImageSrc(itemPackageImage) {
    // console.log(itemPackageImage)
    // let imageData = 'data:' + itemImg.itemImgType + ';base64,' + itemImg.itemImg;
    // return this.sanitizer.bypassSecurityTrustUrl(imageData);
    return this.sanitizer.bypassSecurityTrustUrl(environment.image_url + itemPackageImage.imgName);
  }

  getQty(qty) {
    let qtys = ((qty / 1000) + '').split('.');
    if (qtys.length === 2) {
      return ((qty / 1000) + '').split('.')[0] + 'Kg ' + ((qty % 1000) + '').split('.')[0] + 'g'
    }
    return qtys[0] + 'Kg'
  }
}
