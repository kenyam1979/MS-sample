import React, { Component } from 'react';
import request from 'superagent';
import OrderForm from './OrderForm';
import './OrderApp.css'



class OrderApp extends Component {

  constructor(props) {
    super(props);
    this.state = {
      orders: [],
    };
  }

  componentDidMount() {
    this.fetchOrders();
  }

  fetchOrders(e) {
    request
      .get('http://localhost:3001/api/order/')
      .then(res => {
        this.setState({ orders: res.body });
      })
      .catch(err => {
        console.error(err);
      });

  }

  fetchShipment(e) {
    const id = e.currentTarget.getAttribute('currentorderid');
    request
      .get('http://localhost:3001/api/shipment/order/' + id)
      .then(res => {
        this.setState({
          id: res.body.id,
          shippingAddress: res.body.shippingAddress,
          shippingDate: res.body.shippingDate,
          status: res.body.status
        });
      })
      .catch(err => {
        console.error(err);
        this.setState({
          id: 'Error',
          shippingAddress: 'Error',
          shippingDate: 'Error',
          status: 'Error'
        });
      });

  }


  dispatchShipment(e) {
    const id = e.currentTarget.getAttribute('shipmentid');
    request
      .get('http://localhost:3001/api/shipment/dispatch/' + id)
      .then(res => {
        this.fetchOrders();
        this.setState({
          id: res.body.id,
          shippingAddress: res.body.shippingAddress,
          shippingDate: res.body.shippingDate,
          status: res.body.status
        });
      })
      .catch(err => {
        console.error(err);
      });

  }

  completeShipment(e) {
    const id = e.currentTarget.getAttribute('shipmentid');
    request
      .get('http://localhost:3001/api/shipment/complete/' + id)
      .then(res => {
        this.fetchOrders();
        this.setState({
          id: res.body.id,
          shippingAddress: res.body.shippingAddress,
          shippingDate: res.body.shippingDate,
          status: res.body.status
        });
      })
      .catch(err => {
        console.error(err);
      });

  }

  render() {

    const tableHeader = (
      <tr key="header">
        <th>Id</th>
        <th>Customer</th>
        <th>Item</th>
        <th>Qty</th>
        <th>Status</th>
      </tr>
    );

    const tableItems = this.state.orders.map(order => (
      <tr key={order.id} onClick={e => this.fetchShipment(e)} currentorderid={order.id}>
        <td>{order.id}</td>
        <td>{order.customer}</td>
        <td>{order.item}</td>
        <td>{order.qty}</td>
        <td>{order.status}</td>
      </tr>
    ));

    return (
      <div className='orderapp'>
        <h2>Order entry</h2>
        <OrderForm onPost={e => this.fetchOrders(e)} />
        <h2>Orders</h2>
        <table className='orderapp__table'>
          <thead>{tableHeader}</thead>
          <tbody>{tableItems}</tbody>
        </table>
        <h2>Delivery status</h2>

        <div className='orderapp__delivery'>
          <ul>
            <li>Delivery Id: {this.state.id}</li>
            <li>Address: {this.state.shippingAddress}</li>
            <li>Date: {this.state.shippingDate}</li>
            <li>Status: {this.state.status}</li>
          </ul>
          <a href='#' onClick={e => this.dispatchShipment(e)} shipmentid={this.state.id}>Dispatch</a>
          <a href='#' onClick={e => this.completeShipment(e)} shipmentid={this.state.id}>Completed</a>
        </div>
      </div>
    );
  }


}

export default OrderApp;