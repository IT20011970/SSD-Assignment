import {Component, OnInit} from '@angular/core';
import {FarmerService} from "../../../../_service/farmer.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-farmer-items-manage',
  templateUrl: './farmer-items-manage.component.html',
  styleUrls: ['./farmer-items-manage.component.css']
})
export class FarmerItemsManageComponent implements OnInit {

  item;
  submitType = 'Add';

  file: File = null;

  constructor(private farmerS: FarmerService, private router: Router) {
    this.item = this.getItem();
  }

  ngOnInit(): void {
    if (this.farmerS.item !== undefined) {
      this.item = this.farmerS.item;
      this.submitType = 'Update';
    } else {
      this.submitType = 'Add';
    }
  }

  onChange(event) {
    this.file = event.target.files[0];
  }

  onSubmit() {
    const formData = new FormData();
    if (this.file !== null) {
      formData.append("file", this.file, this.file.name);
    }
    formData.append('item', new Blob([JSON.stringify(this.item)],
      {
        type: "application/json"
      }));

    if (this.submitType === 'Add') {
      this.farmerS.addItem(formData).subscribe((item) => {
        this.router.navigate(['/main/farmer/view_items'])
      })
    } else {
      this.farmerS.updateItem(formData, this.item.itemId).subscribe((item) => {
        this.router.navigate(['/main/farmer/view_items'])
      })
    }
  }

  removeItem(item) {
    this.farmerS.removeItem(item.itemId).subscribe(() => {
      this.router.navigate(['/main/farmer/view_items'])
    })
  }

  getItem() {
    return {
      description: '',
      price: 0,
      qty: 0,
      userAccount: JSON.parse(localStorage.getItem('user'))
    }
  }
}
