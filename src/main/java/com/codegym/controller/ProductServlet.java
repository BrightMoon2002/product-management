package com.codegym.controller;

import com.codegym.dao.productDAO.ProductDAO;
import com.codegym.dao.typeProductDAO.ITypeProductDAO;
import com.codegym.dao.typeProductDAO.TypeProductDAO;
import com.codegym.model.Product;
import com.codegym.model.TypeProduct;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "/products")
public class ProductServlet extends HttpServlet {

    private ProductDAO productDAO;
    private ITypeProductDAO typeProductDAO = new TypeProductDAO();

    public void init() {
        productDAO = new ProductDAO();

    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showNewForm(req, resp);
                break;
            case "edit":
                showEditForm(req, resp);
                break;
            case "delete":
                showDeleteForm(req, resp);
                break;
            case "search":
                showSearchProductByProducer(req, resp);
                break;
            case "searchP":
                showSearchForm(req, resp);
                break;
            case "searchD":
                showSearchProductByDescription(req, resp);
                break;
            case "sortByName":
                showListByName(req, resp);
                break;
            default:
                listProduct(req, resp);
                break;
        }

    }

    private void showListByName(HttpServletRequest req, HttpServletResponse resp) {
        List<Product> productList;
        productList = productDAO.sortByName();
        req.setAttribute("list", productList);
        RequestDispatcher dispatcher;
        if (productList == null) {
            dispatcher = req.getRequestDispatcher("product/error-404.jsp");
        } else {
            dispatcher = req.getRequestDispatcher("product/list.jsp");
        }
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showSearchProductByDescription(HttpServletRequest req, HttpServletResponse resp) {
        String description = req.getParameter("searchD");
        List<Product> productList;
        productList = productDAO.selectProductByDescription(description);
        req.setAttribute("list", productList);
        RequestDispatcher dispatcher;
        if (productList == null) {
            dispatcher = req.getRequestDispatcher("product/error-404.jsp");
        } else {
            dispatcher = req.getRequestDispatcher("product/list.jsp");
        }
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showSearchForm(HttpServletRequest req, HttpServletResponse resp) {
        String producer = req.getParameter("producer");
        req.setAttribute("producer", producer);
        RequestDispatcher dispatcher = req.getRequestDispatcher("product/searchByProducer.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showSearchProductByProducer(HttpServletRequest req, HttpServletResponse resp) {
        String producer = req.getParameter("searchP");
        List<Product> productList;
        productList = productDAO.selectProductByProducer(producer);
        req.setAttribute("list", productList);
        RequestDispatcher dispatcher;
        if (productList == null) {
            dispatcher = req.getRequestDispatcher("product/error-404.jsp");
        } else {
            dispatcher = req.getRequestDispatcher("product/list.jsp");
        }
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void showNewForm(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("typeList", typeProductDAO.selectAllProduct());
        RequestDispatcher dispatcher = req.getRequestDispatcher("product/create.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void listProduct(HttpServletRequest req, HttpServletResponse resp) {
        List<Product> productList = productDAO.selectAllProduct();
        String name = req.getParameter("searchP");
        if (name == null) {
            productList = productDAO.selectAllProduct();

        } else {
            productList = productDAO.selectProductByProducer(name);
            req.setAttribute("list", productList);
            RequestDispatcher dispatcher;
            if (productList == null) {
                dispatcher = req.getRequestDispatcher("product/error-404.jsp");
            } else {
                dispatcher = req.getRequestDispatcher("product/list.jsp");
            }
            try {
                dispatcher.forward(req, resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        req.setAttribute("list", productList);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("product/list.jsp");
        try {
            requestDispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createNewProduct(req, resp);
                break;
            case "edit":
                editProduct(req, resp);
                break;
            case "delete":
                deleteProduct(req, resp);
                break;
            case "searchP":
                showSearchProductByProducer(req, resp);
                break;
            default:
                listProduct(req, resp);
                break;
        }
    }

    private void showDeleteForm(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        Product productDelete = productDAO.selectProduct(id);
        req.setAttribute("productDelete", productDelete);
        RequestDispatcher dispatcher = req.getRequestDispatcher("product/delete.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteProduct(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        Product productDelete = productDAO.selectProduct(id);
        RequestDispatcher dispatcher;
        if (productDelete == null) {
            dispatcher = req.getRequestDispatcher("product/error-404.jsp");
            try {
                dispatcher.forward(req, resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            productDAO.deleteProduct(id);
            try {
                resp.sendRedirect("/products");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        Product productEdit = productDAO.selectProduct(id);
        req.setAttribute("productEdit", productEdit);
        req.setAttribute("typeList", typeProductDAO.selectAllProduct());
        RequestDispatcher dispatcher = req.getRequestDispatcher("product/edit.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void editProduct(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String producer = req.getParameter("producer");
        double price = Double.parseDouble(req.getParameter("price"));
        int idTypeProduct = Integer.parseInt(req.getParameter("type"));
        TypeProduct typeProduct = typeProductDAO.selectProduct(idTypeProduct);
        Product productEdit = productDAO.selectProduct(id);
        RequestDispatcher dispatcher;
        if (productEdit == null) {
            dispatcher = req.getRequestDispatcher("product/error-404.jsp");
            try {
                dispatcher.forward(req, resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            productEdit = new Product(id, name, description, producer, price, typeProduct);
            productDAO.updateProduct(productEdit);
            try {
                resp.sendRedirect("/products");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    private void createNewProduct(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String producer = req.getParameter("producer");
        double price = Double.parseDouble(req.getParameter("price"));
        int idTypeProduce = Integer.parseInt(req.getParameter("type"));
        TypeProduct typeProduct = typeProductDAO.selectProduct(idTypeProduce);
        Product product = new Product(name, description, producer, price, typeProduct);
        try {
            productDAO.insertProduct(product);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            resp.sendRedirect("/products");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
