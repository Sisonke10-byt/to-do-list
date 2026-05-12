# Kubernetes Deployment Guide for To-Do List App

## Prerequisites

- [microk8s](https://microk8s.io/) installed and running
- `kubectl` configured to access your microk8s cluster
- Docker image built locally: `to-do-list:latest`

## Quick Start

### 1. Build the Docker image

```bash
cd /home/sisonke/to-do-list
./mvnw clean package
docker build -t to-do-list:latest .
```

### 2. Load the image into microk8s

```bash
microk8s ctr image import <(docker save to-do-list:latest)
```

Or, if using containerd directly:

```bash
docker save to-do-list:latest | microk8s ctr -n k8s.io image import -
```

### 3. Deploy to microk8s using Kustomize

```bash
cd /home/sisonke/to-do-list
kubectl apply -k k8s/
```

### 4. Verify the deployment

```bash
# Check namespace
kubectl get ns | grep to-do-list

# Check pods
kubectl get pods -n to-do-list

# Check services
kubectl get svc -n to-do-list

# View logs
kubectl logs -n to-do-list -l app=todo-app
kubectl logs -n to-do-list -l app=postgres
```

### 5. Access the application

- **NodePort URL**: `http://localhost:30080`
- **Using port-forward**: 
  ```bash
  kubectl port-forward -n to-do-list svc/todo-app 8080:8080
  ```
  Then open `http://localhost:8080`

## Manifest Structure

```
k8s/
├── namespace.yaml              # Kubernetes namespace
├── postgres-secret.yaml        # DB credentials secret
├── postgres-pvc.yaml          # PersistentVolumeClaim (unused if StatefulSet volumeClaimTemplates)
├── postgres-statefulset.yaml  # PostgreSQL database
├── postgres-service.yaml      # Headless service for PostgreSQL
├── app-config.yaml            # ConfigMap for app configuration
├── app-secret.yaml            # Secret for app credentials
├── app-deployment.yaml        # To-Do List app deployment (2 replicas)
├── app-service.yaml           # Service exposing the app (NodePort)
└── kustomization.yaml         # Kustomize configuration
```

## Cleanup

To remove all resources:

```bash
kubectl delete -k k8s/
```

## Scaling the app

Change the replicas in `app-deployment.yaml`:

```yaml
spec:
  replicas: 3  # Change this value
```

Then reapply:

```bash
kubectl apply -k k8s/
```

## Viewing logs in real-time

```bash
# App logs
kubectl logs -f -n to-do-list -l app=todo-app

# PostgreSQL logs
kubectl logs -f -n to-do-list -l app=postgres
```

## Debugging

- **Check pod events**: `kubectl describe pod <pod-name> -n to-do-list`
- **Shell into pod**: `kubectl exec -it <pod-name> -n to-do-list -- /bin/bash`
- **Check service endpoints**: `kubectl get endpoints -n to-do-list`
