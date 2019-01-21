import React from 'react';
import axios from 'axios';

import {Input, FormGroup, Label, Modal, ModalHeader, ModalBody, ModalFooter, Table, Button} from 'reactstrap';
import { Dropdown, DropdownToggle, DropdownMenu, DropdownItem } from 'reactstrap';
import {Storage} from "react-jhipster";

class ProductPage extends React.Component {

  state = {
    products: []

  };

  componentWillMount(){
    axios.get("http://localhost:8080/productAPI/products")
      .then((response) => {
      this.setState({
        products: response.data.content
      })
    });
  }

  render() {

    let products = this.state.products.map((product) => {
      return(
        <tr key={product.id}>
          <td>{product.id}</td>
          <td>{product.name}</td>
          <td>{product.unitprice}</td>
          <td>{product.description}</td>
          {/*<td>{product.available}</td>*/}
          <td>
            <Button color="success" size="sm" className="mr-2">Edit</Button>
            <Button color="danger" size="sm" >Danger</Button>
          </td>
        </tr>
      )
    });

    return (
      <div className="App container">
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
