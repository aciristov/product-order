import React from 'react';
import axios from 'axios';

import {Input, FormGroup, Label, Modal, ModalHeader, ModalBody, ModalFooter, Table, Button} from 'reactstrap';
import { Dropdown, DropdownToggle, DropdownMenu, DropdownItem } from 'reactstrap';
import {Storage} from "react-jhipster";

class ProductPage extends React.Component {

  state = {
    products: []

  };

  // constructor(props){
  //   super(props);
  //
  //   this.toggle = this.toggle.bind(this);
  //   this.state = {
  //     dropdownOpen: false
  //   };
  // }

  // toggle() {
  //   this.setState(prevState => ({
  //     dropdownOpen: !prevState.dropdownOpen
  //   }));
  // }

  componentWillMount() {
    const token = Storage.local.get('jhi-authenticationToken') || Storage.session.get('jhi-authenticationToken');
    axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;

    // axios.get('http://localhost:8080/productAPI/companies' + companyId + '/products').then(response => {
    axios.get('http://localhost:8080/productAPI/companies/3/products').then(response => {
      const products = response.data.component;
      this.setState({ products });
    });

  }

  getProductsForCompany(companyId) {

  }

  render() {

    let products = this.state.products.map(product => {
      return (
        <tr key={product.id}>
          <td>{product.id}</td>
          <td>{product.name}</td>
          <td>{product.unitprice}</td>
          <td>{product.description}</td>
          <td>{product.available}</td>
          <td>
            <Button color="success" size="sm" className="mr-2">Edit</Button>
            <Button color="danger" size="sm">Delete</Button>
          </td>

        </tr>
      )
    });

    return (
      <div className="App container">

        <h3><strong> Products </strong></h3>

        //MODAL FORM FOR ADDING PRODUCT TO COMPANY!

        {/*<Modal isOpen={this.state.newCompanyModal} toggle={this.toggleNewCompanyModal.bind(this)}>*/}
          {/*<ModalHeader toggle={this.toggleNewCompanyModal.bind(this)}>Add a new company</ModalHeader>*/}

          {/*<ModalBody>*/}
            {/*<FormGroup>*/}
              {/*<Label for="name"> Name </Label>*/}
              {/*<Input id="name" value={this.state.newCompanyData.name} onChange={(e) => {*/}
                {/*let { newCompanyData } = this.state;*/}

                {/*newCompanyData.name = e.target.value;*/}

                {/*this.setState( { newCompanyData });*/}
              {/*}} />*/}
            {/*</FormGroup>*/}

            {/*<FormGroup>*/}
              {/*<Label for="city"> City </Label>*/}
              {/*<Input id="city" value={this.state.newCompanyData.city} onChange={(e) => {*/}

                {/*let { newCompanyData } = this.state;*/}

                {/*newCompanyData.city = e.target.value;*/}

                {/*this.setState( { newCompanyData });*/}

              {/*}} />*/}
            {/*</FormGroup>*/}

            {/*<FormGroup>*/}
              {/*<Label for="phone"> Phone </Label>*/}
              {/*<Input id="phone" value={this.state.newCompanyData.phone} onChange={(e) => {*/}
                {/*let { newCompanyData } = this.state;*/}

                {/*newCompanyData.phone = e.target.value;*/}

                {/*this.setState({ newCompanyData });*/}
              {/*}}/>*/}
            {/*</FormGroup>*/}

            {/*<FormGroup>*/}
              {/*<Label for="address"> Address </Label>*/}
              {/*<Input id="address" value={this.state.newCompanyData.address} onChange={(e) => {*/}

                {/*let { newCompanyData } = this.state;*/}

                {/*newCompanyData.address = e.target.value;*/}

                {/*this.setState({ newCompanyData });*/}


              {/*}} />*/}


            {/*</FormGroup>*/}

          {/*</ModalBody>*/}

          {/*<ModalFooter>*/}

            {/*<Button color="primary" onClick={this.addCompany.bind(this)}>Add Company</Button>{' '}*/}
            {/*<Button color="secondary" onClick={this.toggleNewCompanyModal.bind(this)}>Cancel</Button>*/}
          {/*</ModalFooter>*/}
        {/*</Modal>*/}

        <Table>
          <thead>
          <tr>
            <th>#</th>
            <th>Name</th>
            <th>Price</th>
            <th>Description</th>
            <th>Available</th>
            <th>Actions</th>
          </tr>
          </thead>

          <tbody>
          <tr>
            {/*<td>1</td>*/}
            {/*<td>Cokolado</td>*/}
            {/*<td>60</td>*/}
            {/*<td>Mlecno</td>*/}
            {/*<td>Available</td>*/}
            {/*<td>*/}
              {/*<Button color="success" size="sm" className="mr-2">Edit</Button>*/}
              {/*<Button color="danger" size="sm">Delete</Button>*/}
            {/*</td>*/}

            {products}

          </tr>


          </tbody>

        </Table>

      </div>
    );

  }
}

export default ProductPage;
