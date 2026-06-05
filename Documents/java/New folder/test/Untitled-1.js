class Milestone1 extends TinyPLListener {
    constructor() {
        super();
        this.variables = {}; 
    }

    enterVarDecl(ctx) {
        const name = ctx.Name().getText();
        this.variables[name] = null;
    }

    enterAssign(ctx) {
        const name = ctx.Name().getText();
        const val = ctx.Val().getText();
        this.variables[name] = isNaN(val) ? this.variables[val] : parseInt(val);
    }

    enterCall(ctx) {
        const name = ctx.Name().getText();
        if (name === 'print') {
        
        }
    }
}