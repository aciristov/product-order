import React from 'react';
import axios from 'axios';
import {Storage} from "react-jhipster";
import {Input, FormGroup, Label, Modal, ModalHeader, ModalBody, ModalFooter, Table, Button} from 'reactstrap';
import { Dropdown, DropdownToggle, DropdownMenu, DropdownItem } from 'reactstrap';

class OrderPage extends React.Component{

  state = {
    orders : [],
  };

  componentWillMount(){
    const token = Storage.local.get('jhi-authenticationToken') || Storage.session.get('jhi-authenticationToken');
    axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
    axios.get('http://localhost:8080/orderAPI/user/orders')
      .then((response) => {
        this.setState({
          orders: response.data.content
        })
      });
  }

  render () {

    let orders = this.state.orders.map((order) => {
      return (
        <tr key={order.id}>
          <td>{order.id}</td>
          <td>{order.description}</td>
          <td>{order.quantity}</td>
          <td>{order.orderDate}</td>
          <td>
            <Button color="success" size="sm" className="mr-2">Edit</Button>
            <Button color="danger" size="sm" >Delete</Button>
          </td>
        </tr>
      )
    });

    return (

      <Table>
        <thead>
        <tr>
          <th>#</th>
          <th>Description</th>
          <th>Quantity</th>
          <th>Order Date</th>
        </tr>
        </thead>
        <tbody>
        {orders}
        </tbody>
      </Table>


    )
  }


}

export default OrderPage;
