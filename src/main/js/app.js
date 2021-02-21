const React = require('react');
const ReactDOM = require('react-dom');

class Product extends React.Component{
	render() {
		return (
			<tr>
				<td>{this.props.product.name}</td>
				<td>{this.props.product.description}</td>
				<td>{this.props.product.price}</td>
			</tr>
		)
	}
}

class ProductList extends React.Component{
	render() {
		const products = this.props.products.map(product =>
			<Product key={product._links.self.href} product={product}/>
		);
		return (
			<table>
				<tbody>
					<tr>
						<th>Name</th>
						<th>Description</th>
						<th>Price</th>
					</tr>
					{products}
				</tbody>
			</table>
		)
	}
}

class App extends React.Component {

	constructor(props) {
		super(props);
		this.state = {products: []};
	}

	componentDidMount() {
		fetch('/api/product')
            .then((response) => response.json())
            .then((data) => {
                this.setState({products: data._embedded.product});
                console.log(products);
            });

	}

	render() {
		return (
		<>
            <h1>toto</h1>
            <ProductList products={this.state.products}/>
		</>
		)
	}
}


ReactDOM.render(
	<App />,
	document.getElementById('react')
)
