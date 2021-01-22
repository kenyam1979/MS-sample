import React, { Component } from 'react';
import request from 'superagent';


class OrderForm extends Component {

  constructor(props) {
    super(props);
    this.state = {
      customer: '',
      item: '',
      qty: ''
    };
  }

  doChange(e) {
    const value = e.target.value;
    const key = e.target.name;
    this.setState({
      [key]: value
    });
  }

  doSubmit(e) {
    request
      .post('http://localhost:8080/api/order/')
      .send({
        customer: this.state.customer,
        item: this.state.item,
        qty: this.state.qty
      })
      .then(res => {
        console.log('Order created: ' + res + ' as ' + this.state);
        if (this.props.onPost) {
          this.props.onPost();
        }
      })
      .catch(err => {
        console.error(err);
      })

    e.preventDefault();
  }

  render() {
    return (
      <div className='orderfrom'>
        <form onSubmit={e => this.doSubmit(e)}>
          Customer:
          <input name='customer' 
            type='text'
            value={this.state.customer}
            onChange={e => this.doChange(e)} /><br />
          Item: 
          <input name='item'
            type='text'
            value={this.state.item}
            onChange={e => this.doChange(e)} /><br />
          Qty:
          <input name='qty'
            type='text'
            value={this.state.qty}
            onChange={e => this.doChange(e)} /><br />
          <input type='submit' value='Register' />
        </form>
      </div>
    );
  }


}

export default OrderForm;