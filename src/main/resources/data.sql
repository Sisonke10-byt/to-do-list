-- Sample data for To-Do List application
INSERT INTO todos (title, description, completed, due_date) VALUES
('Complete project documentation', 'Write comprehensive documentation for the to-do list project', false, '2026-05-01'),
('Review code changes', 'Review the latest pull request for the authentication module', false, '2026-04-25'),
('Update dependencies', 'Update all npm packages to their latest versions', true, '2026-04-20'),
('Setup CI/CD pipeline', 'Configure GitHub Actions for automated testing and deployment', false, '2026-04-30'),
('Database optimization', 'Optimize database queries for better performance', false, '2026-05-15'),
('User interface improvements', 'Enhance the UI/UX based on user feedback', true, '2026-04-18'),
('Security audit', 'Perform security audit on the application', false, '2026-05-10'),
('API documentation', 'Create OpenAPI/Swagger documentation for REST endpoints', false, '2026-05-05');