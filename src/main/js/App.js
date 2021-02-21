const React = require('react');
const ReactDOM = require('react-dom');

export default class App extends React.Component {
	constructor(props) {
		super(props);
		this.state = {products: [], links: []};
	}

    handleNavFirst(e){
        e.preventDefault();
        this.props.onNavigate(this.props.links.first.href);
    }

    handleNavPrev(e) {
        e.preventDefault();
        this.props.onNavigate(this.props.links.prev.href);
    }

    handleNavNext(e) {
        e.preventDefault();
        this.props.onNavigate(this.props.links.next.href);
    }

    handleNavLast(e) {
        e.preventDefault();
        this.props.onNavigate(this.props.links.last.href);
    }

	componentDidMount() {
		fetch('/api/products?size=12')
            .then((response) => response.json())
            .then((data) => {
                this.setState({
                    products: data._embedded.products,
                    links: data._links,
			    });
            });
	}

	render() {
	    const navLinks = [];
    	if ("first" in this.state.links) {
    		navLinks.push(<button key="first" onClick={this.handleNavFirst}>&lt;&lt;</button>);
    	}
    	if ("prev" in this.state.links) {
    		navLinks.push(<button key="prev" onClick={this.handleNavPrev}>&lt;</button>);
    	}
    	if ("next" in this.state.links) {
    		navLinks.push(<button key="next" onClick={this.handleNavNext}>&gt;</button>);
    	}
    	if ("last" in this.state.links) {
    		navLinks.push(<button key="last" onClick={this.handleNavLast}>&gt;&gt;</button>);
    	}

		return (
            <main className="container">
                <h1 className="h1-title">Product Land</h1>
                <ProductList products={this.state.products}/>
                <div>
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
                    <h2>{this.props.product.name}</h2>
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