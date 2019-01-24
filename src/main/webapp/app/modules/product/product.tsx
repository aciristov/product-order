import React from 'react';
import axios from 'axios';

import {Input, FormGroup, Label, Modal, ModalHeader, ModalBody, ModalFooter, Table, Button} from 'reactstrap';
import { Dropdown, DropdownToggle, DropdownMenu, DropdownItem } from 'reactstrap';
import {Storage} from "react-jhipster";
import {bool} from "prop-types";

import ReactDOM from 'react-dom';
import {Route, withRouter} from 'react-router-dom';

class ProductPage extends React.Component {

  state = {
    products: [],
    companies: [],

    newProductData: {
      name: '',
      unitprice: '',
      description: '',
      available: bool,
      company: ''
    },

    editProductData: {
      id: '',
      name: '',
      unitprice: '',
      description: '',
      available: '',
      company: ''
    },

    newProductModal: false,
    editProductModal: false


  };

  componentWillMount(){

    const token = Storage.local.get('jhi-authenticationToken') || Storage.session.get('jhi-authenticationToken');

    axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;

    axios.get("http://localhost:8080/productAPI/products")
      .then((response) => {
      this.setState({
        products: response.data.content
      })
    });

    axios.get("http://localhost:8080/companyAPI/companies")
      .then((response) => {
        this.setState({
          companies: response.data.content
        })
      });
  }

  createNewProduct(company_id){
    axios.post('http://localhost:8080/productAPI/companies/' + company_id + '/products', this.state.newProductData)
      .then(response => {
        let { products } = this.state;
        console.log("RESPONSE : ", + response);
        console.log(response.data);
        console.log(response.data.content);
        products.push(response.data);

        this.setState({
          products,
          newProductModal: false,
          newProductData : {
            name: '',
            unitprice: '',
            description: '',
            available: bool,
            company: ''
          }
        });
        console.log(response.data.content);
      });

  }

  updateProduct(){
    let {name, unitprice, description, available} = this.state.editProductData;
    axios.put(`http://localhost:8080/productAPI/companies/` + this.state.editProductData.company + '/products/' + this.state.editProductData.id , {
      name, unitprice, description, available
    }).then((response) => {
      let { products } = this.state;
      console.log(response);
      const updatedProduct = response.data;

      const updatedProducts = products.map(product => {
        if (product.id !== updatedProduct.id) {
          return product;
        }
        return updatedProduct;
      });
      this.setState({ ...this.state,  products: updatedProducts});
      this.toggleEditProductModal();
    });
  }

  editProduct(id, name, unitprice, description, available){
    this.setState({ editProductData: {
        id, name, unitprice, description, available
      }, editProductModal: ! this.state.editProductModal
    });
  }

  deleteProduct(id){
    axios.delete('http://localhost:8080/productAPI/companies/1/products/'+ id).then(response =>{
      let { products } = this.state;

      const filterProducts = products.filter(product => product.id !== id);
      this.setState({ ...this.state,  products: filterProducts});
    });
  }

  toggleNewProductModal(){
    this.setState({
      newProductModal: ! this.state.newProductModal
    });
  }

  toggleEditProductModal() {
    this.setState({
      editProductModal: ! this.state.editProductModal
    });
  }

  // TODO : UPDATE FOR PRODUCT
  // TODO : AUTOCOMPLETE FOR SEARCHING, SEE ALSO https://alligator.io/react/react-autocomplete/

  render() {

    let products = this.state.products.map((product) => {
      return(
        <tr key={product.id}>
          <td>{product.id}</td>
          <td>{product.name}</td>
          <td>{product.unitprice}</td>
          <td>{product.description}</td>
          <td>{product.available}</td>
          <td>
            <Button color="success" size="sm" className="mr-2" onClick={this.editProduct.bind(this, product.id, product.name, product.unitprice, product.description, product.available)}>Edit</Button>
            <Button color="danger" size="sm" onClick={this.deleteProduct.bind(this, product.id)} >Delete</Button>
          </td>
        </tr>
      )
    });


    let companies_search = this.state.companies;
    let optionItems = companies_search.map((company) =>
      <option key={company.name}>{company.name}</option>  /* show just name for each company */
    );

    return (
      <div className="App container">

        {/*MODAL FOR POST MODAL, CREATE PRODUCT!!!*/}

        <br/><Button className="my-1" color="primary" onClick={this.toggleNewProductModal.bind(this)}>Add Product</Button><br/><br/>

        <Modal isOpen={this.state.newProductModal} toggle={this.toggleNewProductModal.bind(this)}>
          <ModalHeader toggle={this.toggleNewProductModal.bind(this)}>Add new product</ModalHeader>

          <ModalBody>

              <FormGroup>
                <Label for="name"> Name </Label>
                <Input id="name" value={this.state.newProductData.name} onChange={(e) => {

                  let { newProductData } = this.state;

                  newProductData.name = e.target.value;

                  this.setState( { newProductData });

                }} />
              </FormGroup>

              <FormGroup>
                <Label for="unitprice"> Price </Label>
                <Input id="unitprice" value={this.state.newProductData.unitprice} onChange={(e) => {

                  let { newProductData } = this.state;

                  newProductData.unitprice = e.target.value;

                  this.setState( { newProductData });

                }} />
              </FormGroup>

              <FormGroup>
                <Label for="description"> Description </Label>
                <Input id="description" value={this.state.newProductData.description} onChange={(e) => {

                  let { newProductData } = this.state;

                  newProductData.description = e.target.value;

                  this.setState( { newProductData });

                }} />
              </FormGroup>

              <FormGroup>
                <Label for="available"> Check if it's available: </Label> <br />
                <Input type="checkbox" id="available" value={this.state.newProductData.available} className="pagination" onChange={(e) => {

                  let { newProductData } = this.state;

                  newProductData.available = e.target.value;

                  this.setState( { newProductData });

                }} />
              </FormGroup>

              <FormGroup>

                <Label>Select for which company: </Label>
                <div>
                      <Input type="select" id="option" value={this.state.newProductData.company} onChange={(e) => {

                        let { newProductData } = this.state;

                        newProductData.company = e.target.value;

                        this.setState( { newProductData });

                      }}
                      > {optionItems} </Input>


                </div>

              </FormGroup>

          </ModalBody>

          <ModalFooter>

            <Button color="primary" onClick={this.createNewProduct.bind(this)}>Add Product</Button>{' '}
            <Button color="secondary" onClick={this.toggleNewProductModal.bind(this)}>Cancel</Button>
          </ModalFooter>
        </Modal>

        {/*END MODAL FOR CREATE!!! */}


        /* MODAL FOR EDITING PRODUCT */

        <Modal isOpen={this.state.editProductModal} toggle={this.toggleEditProductModal.bind(this)}>
          <ModalHeader toggle={this.toggleEditProductModal.bind(this)}>Edit product</ModalHeader>

          <ModalBody>

            <FormGroup>
              <Label for="name"> Name </Label>
              <Input id="name" value={this.state.editProductData.name} onChange={(e) => {

                let { editProductData } = this.state;

                editProductData.name = e.target.value;

                this.setState({ editProductData });

              }} />
            </FormGroup>

            <FormGroup>
              <Label for="unitprice"> Unitprice </Label>
              <Input id="unitprice" value={this.state.editProductData.unitprice} onChange={(e) => {

                let { editProductData } = this.state;

                editProductData.unitprice = e.target.value;

                this.setState({ editProductData });

              }}
              />
            </FormGroup>

            <FormGroup>
              <Label for="description"> Description </Label>
              <Input id="description" value={this.state.editProductData.description} onChange={(e) => {

                let { editProductData } = this.state;

                editProductData.description = e.target.value;

                this.setState({ editProductData });

              }}
              />
            </FormGroup>

            <FormGroup>
              <Label for="available"> Available </Label>
              <Input id="available" value={this.state.editProductData.available} onChange={(e) => {

                let { editProductData } = this.state;

                editProductData.available = e.target.value;

                this.setState({ editProductData });

              }}
              />
            </FormGroup>

          </ModalBody>

          <ModalFooter>

            <Button color="primary" onClick={this.updateProduct.bind(this)}>Update Product</Button>{' '}
            <Button color="secondary" onClick={this.toggleEditProductModal.bind(this)}>Cancel</Button>
          </ModalFooter>
        </Modal>

        /* END MODAL FOR EDIT */


        <Table>
          <thead>
          <tr>
            <th>#</th>
            <th>Name</th>
            <th>Unitprice</th>
            <th>Description</th>
            {/*<th>Is available</th>*/}
          </tr>
          </thead>

          <tbody>
          {products}
          </tbody>
        </Table>
      </div>
    );

  }
}

export default ProductPage;
