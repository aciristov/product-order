import React from 'react';
import axios from 'axios';
import {Storage} from "react-jhipster";
import {Input, FormGroup, Label, Modal, ModalHeader, ModalBody, ModalFooter, Table, Button} from 'reactstrap';
import {Dropdown, DropdownToggle, DropdownMenu, DropdownItem} from 'reactstrap';

class OrderPage extends React.Component {

  state = {
    orders: [],

    newOrderModal: false,
    newOrderData: {
      description: '',
      quantity: '',
      orderDate: '',
      user: ''
    },

    editOrderModal: false,
    editOrderData: {
      id: '',
      description: '',
      quantity: '',
      orderDate: '',
      user: '',
    }
  };

  componentWillMount() {
    const token = Storage.local.get('jhi-authenticationToken') || Storage.session.get('jhi-authenticationToken');
    axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
    axios.get('http://localhost:8080/orderAPI/user/orders')
      .then((response) => {
        this.setState({
          orders: response.data.content
        })
      });
  }

  createNewOrder() {
    const newOrder = {
      description: this.state.newOrderData.description,
      quantity: this.state.newOrderData.quantity,
      orderDate: this.state.newOrderData.orderDate,
    };
    axios.post('http://localhost:8080/orderAPI/user/orders', newOrder)
      .then(response => {
        let {orders} = this.state;
        orders.push(response.data);

        this.setState({
          orders,
          newOrderData: {
            description: '',
            quantity: '',
            orderDate: '',
            user: ''
          }
        });
        console.log(response.data.content);
        this.toggleNewOrderModal();
      });
  }

  deleteOrder(id) {
    axios.delete('http://localhost:8080/orderAPI/user/orders/' + id)
      .then(response => {
        let {orders} = this.state;
        const filterOrders = orders.filter(order => order.id !== id);
        this.setState({
          ...this.state, orders: filterOrders
        });
      });
  }

  editOrder(id, description, quantity, orderDate) {
    this.setState({
      editOrderData: {
        id, description, quantity, orderDate
      }, editOrderModal: !this.state.editOrderModal
    });
  }

  updateOrder() {
    let {description, quantity, orderDate} = this.state.editOrderData;
    axios.put('http://localhost:8080/orderAPI/user/orders/' + this.state.editOrderData.id, {
      description, quantity, orderDate
    }).then((response) => {
      let {orders} = this.state;
      console.log(response);
      const updateOrder = response.data;

      const updatedOrders = orders.map(order => {
        if ( order.id !== updateOrder.id){
        return order;
      }
      return updateOrder;
      });
      this.setState({ ...this.state, orders: updatedOrders});
      this.toggleEditOrderModal();
    });
  }

  toggleNewOrderModal() {
    this.setState({
      newOrderModal: !this.state.newOrderModal
    });
  }

  toggleEditOrderModal() {
    this.setState({
      editOrderModal: !this.state.editOrderModal
    });
  }

  render() {

    let orders = this.state.orders.map((order) => {
      return (
        <tr key={order.id}>
          <td>{order.id}</td>
          <td>{order.description}</td>
          <td>{order.quantity}</td>
          <td>{order.orderDate}</td>
          <td>
            <Button color="success" size="sm" className="mr-2"
                    onClick={this.editOrder.bind(
                      this, order.id, order.description, order.quantity, order.orderDate)}>Edit</Button>
            <Button color="danger" size="sm" onClick={this.deleteOrder.bind(this, order.id)}>Delete</Button>
          </td>
        </tr>
      )
    });

    return (
      <div className="App container">
        <br/><Button className="my-1" color="primary" onClick={this.toggleNewOrderModal.bind(this)}>Make new
        order</Button><br/><br/>

        {/* POST NEW ORDER */}

        <Modal isOpen={this.state.newOrderModal} toggle={this.toggleNewOrderModal.bind(this)}>
          <ModalHeader toggle={this.toggleNewOrderModal.bind(this)}> Make new Order </ModalHeader>
          <ModalBody>
            <FormGroup>
              <Label for="description"> Description </Label>
              <Input id="description" value={this.state.newOrderData.description} onChange={(e) => {
                let {newOrderData} = this.state;
                newOrderData.description = e.target.value;
                this.setState({newOrderData})
              }}
              />
            </FormGroup>
            <FormGroup>
              <Label for="quantity"> Quantity </Label>
              <Input id="quantity" value={this.state.newOrderData.quantity} onChange={(e) => {
                let {newOrderData} = this.state;
                newOrderData.quantity = e.target.value;
                this.setState({newOrderData})
              }}
              />
            </FormGroup>
            <FormGroup>
              <Label for="orderDate"> Date </Label>
              <Input id="orderDate" value={this.state.newOrderData.orderDate} onChange={(e) => {
                let {newOrderData} = this.state;
                newOrderData.orderDate = e.target.value;
                this.setState({newOrderData})
              }}
              />
            </FormGroup>


          </ModalBody>

          <ModalFooter>

            <Button color="primary" onClick={this.createNewOrder.bind(this)}>Make order</Button>{' '}
            <Button color="secondary" onClick={this.toggleNewOrderModal.bind(this)}>Cancel</Button>

          </ModalFooter>

        </Modal>

        {/* END OF POST NEW ORDER */}

        {/* MODAL FOR EDITING ORDER */}

        <Modal isOpen={this.state.editOrderModal} toggle={this.toggleEditOrderModal.bind(this)}>
          <ModalHeader toggle={this.toggleEditOrderModal.bind(this)}>Change Order</ModalHeader>

          <ModalBody>
            <FormGroup>
              <Label for="description">Description</Label>
              <Input id="description" value={this.state.editOrderData.description} onChange={(e) => {
                let {editOrderData} = this.state;
                editOrderData.description = e.target.value;
                this.setState({editOrderData});
              }}
              />
            </FormGroup>
            <FormGroup>
              <Label for="quantity">Quantity</Label>
              <Input id="quantity" value={this.state.editOrderData.quantity} onChange={(e) => {
                let {editOrderData} = this.state;
                editOrderData.quantity = e.target.value;
                this.setState({
                  editOrderData
                });
              }}
              />
            </FormGroup>
            <FormGroup>
              <Label for="orderDate">Order Date</Label>
              <Input id="orderDate" value={this.state.editOrderData.orderDate} onChange={(e) => {
                let {editOrderData} = this.state;
                editOrderData.orderDate = e.target.value;
                this.setState({
                  editOrderData
                });
              }}
              />
            </FormGroup>

          </ModalBody>

          <ModalFooter>
            <Button color="primary" onClick={this.updateOrder.bind(this)}>Update Order</Button>
            <Button color="primary" onClick={this.toggleEditOrderModal.bind(this)}>Cancel</Button>
          </ModalFooter>

        </Modal>

        {/* END MODAL EDITING ORDER */}

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

      </div>
  )
  }
  }

  export default OrderPage;
