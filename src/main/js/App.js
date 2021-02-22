const React = require('react');
const ReactDOM = require('react-dom');

export default class App extends React.Component {
	constructor(props) {
		super(props);
		this.state = {products: [], links: [], pageSize: 12};
	}

    onNavigate(navUri) {
    	fetch(navUri)
    	.then((response) => response.ok ? Promise.resolve(response.json()) : Promise.reject("Error !"))
    	.then((data) => {
    		this.setState({
    			products: data._embedded.products,
    			pageSize: this.state.pageSize,
    			links: data._links
    		});
    	});
    }

    componentDidMount() {
        this.loadFromServer(this.state.pageSize);
    }
    loadFromServer(pageSize) {
        fetch('/api/products?size='+pageSize)
        .then((response) => response.ok ? Promise.resolve(response.json()) : Promise.reject("Error !"))
        .then((data) => {
            this.setState({
                products: data._embedded.products,
                links: data._links,
                pageSize: pageSize
            })
        });
    }

	render() {
	    const navLinks = [];
    	if ("first" in this.state.links) {
    		navLinks.push(<button className="button__page" key="first" onClick={this.handleNavFirst}>&lt;&lt;</button>);
    	}
    	if ("prev" in this.state.links) {
    		navLinks.push(<button className="button__page" key="prev" onClick={this.handleNavPrev}>&lt;</button>);
    	}
    	if ("next" in this.state.links) {
    		navLinks.push(<button className="button__page" key="next" onClick={this.handleNavNext}>&gt;</button>);
    	}
    	if ("last" in this.state.links) {
    		navLinks.push(<button className="button__page" key="last" onClick={this.handleNavLast}>&gt;&gt;</button>);
    	}

		return (
            <main className="container">
                <div className="Illustration-1-hex square"></div>
                <div className="Illustration-2-hex square"></div>
                <div className="Illustration-3-hex square"></div>
                <div className="Illustration-4-hex square"></div>
                <div className="Illustration-5-hex square"></div>
                <h1 className="h1-title">Product Land</h1>
                <ProductList products={this.state.products}/>
                <div className="pagination">
                    {navLinks}
                </div>
            </main>
		)
	}
}

class ProductList extends React.Component{
	render() {
		const products = this.props.products.map(product =>
			<Product key={product._links.self.href} product={product}/>
		);
		return (
            <div className="productList">
			    {products}
            </div>
		)
	}
}

class Product extends React.Component{
	render() {
		return (
            <div className="card">
                <div className="product__name">
                    <h2 className="h2-title">{this.props.product.name}</h2>
                </div>
                <div className="product__description">
                    <p>{this.props.product.description}</p>
                </div>
                <div className="product__price">
                    <p>{this.props.product.price}</p>
                </div>
            </div>
		)
	}
}